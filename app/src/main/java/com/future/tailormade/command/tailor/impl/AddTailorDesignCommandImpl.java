package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.image.AddImageCommand;
import com.future.tailormade.command.tailor.AddTailorDesignCommand;
import com.future.tailormade.constants.BaseConstants;
import com.future.tailormade.exceptions.UnauthorizedException;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.model.entity.user.TailorDesign;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.image.AddImageRequest;
import com.future.tailormade.payload.request.tailor.AddTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.UserRepository;
import com.future.tailormade.service.SequenceService;
import com.future.tailormade.utils.SequenceGeneratorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddTailorDesignCommandImpl implements AddTailorDesignCommand {

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private AddImageCommand addImageCommand;

    @Override
    public Mono<AddOrEditTailorDesignResponse> execute(AddTailorDesignRequest request) {
        return findTailor(request.getTailorId())
                .flatMap(tailor -> addDesign(tailor, request))
                .flatMap(userDesignPair ->
                        addTailorDesign(userDesignPair.getFirst(), userDesignPair.getSecond()))
                .map(this::createResponse);
    }

    private Mono<Pair<User, Design>> addDesign(User tailor, AddTailorDesignRequest request) {
        return sequenceService.generateId(request.getTitle(), SequenceGeneratorUtil.DESIGN)
                .map(sequence -> createDesign(sequence, tailor, request))
                .flatMap(this::saveDesign)
                .map(design -> Pair.of(tailor, design));
    }

    private Mono<Design> addTailorDesign(User tailor, Design design) {
        TailorDesign tailorDesign = createTailorDesign(design);
        tailor.addDesign(tailorDesign);
        return userRepository.save(tailor).thenReturn(design);
    }

    private Mono<String> addImage(String id, String image) {
        AddImageRequest request = AddImageRequest.builder()
                .fileName(id)
                .filePath(BaseConstants.IMAGE_PATH_DESIGN)
                .fileInBase64(image)
                .build();
        return addImageCommand.execute(request);
    }

    private AddOrEditTailorDesignResponse createResponse(Design design) {
        AddOrEditTailorDesignResponse response = AddOrEditTailorDesignResponse.builder().build();
        BeanUtils.copyProperties(design, response);
        return response;
    }

    private Design createDesign(String id, User tailor, AddTailorDesignRequest request) {
        Design design = Design.builder().build();
        BeanUtils.copyProperties(request, design);
        design.setId(id);
        design.setTailorName(tailor.getName());
        return design;
    }

    private TailorDesign createTailorDesign(Design design) {
        TailorDesign tailorDesign = TailorDesign.builder().build();
        BeanUtils.copyProperties(design, tailorDesign);
        return tailorDesign;
    }

    private Mono<User> findTailor(String tailorId) {
        return userRepository.findByIdAndRole(tailorId, RoleEnum.ROLE_TAILOR)
                .switchIfEmpty(Mono.error(UnauthorizedException::new));
    }

    private Mono<Design> saveDesign(Design design) {
        return addImage(design.getId(), design.getImage())
                .flatMap(image -> designRepository.save(design));
    }
}
