package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.EditTailorDesignCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.model.entity.user.TailorDesign;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.EditTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EditTailorDesignCommandImpl implements EditTailorDesignCommand {

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<AddOrEditTailorDesignResponse> execute(EditTailorDesignRequest request) {
        return findTailor(request.getTailorId())
                .flatMap(tailor -> editDesign(tailor, request))
                .flatMap(userDesignPair ->
                        editTailorDesign(userDesignPair.getFirst(), userDesignPair.getSecond()))
                .map(this::createResponse);
    }

    private Mono<Pair<User, Design>> editDesign(User tailor, EditTailorDesignRequest request) {
        return findDesign(request.getTailorId(), request.getId())
                .flatMap(design -> saveDesign(design, request))
                .map(design -> Pair.of(tailor, design));
    }

    private Mono<Design> editTailorDesign(User tailor, Design design) {
        TailorDesign tailorDesign = createTailorDesign(design);
        tailor.editDesign(tailorDesign);
        return userRepository.save(tailor).thenReturn(design);
    }

    private AddOrEditTailorDesignResponse createResponse(Design design) {
        AddOrEditTailorDesignResponse response = AddOrEditTailorDesignResponse.builder().build();
        BeanUtils.copyProperties(design, response);
        return response;
    }

    private Mono<Design> saveDesign(Design design, EditTailorDesignRequest request) {
        BeanUtils.copyProperties(request, design);
        return designRepository.save(design);
    }

    private TailorDesign createTailorDesign(Design design) {
        TailorDesign tailorDesign = TailorDesign.builder().build();
        BeanUtils.copyProperties(design, tailorDesign);
        return tailorDesign;
    }
    
    private Mono<Design> findDesign(String tailorId, String id) {
        return designRepository.findByTailorIdAndId(tailorId, id)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private Mono<User> findTailor(String tailorId) {
        return userRepository.findByIdAndRole(tailorId, RoleEnum.ROLE_TAILOR)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }
}
