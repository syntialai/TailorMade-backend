package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.AddTailorDesignCommand;
import com.future.tailormade.model.entity.base.Sequence;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.model.entity.user.TailorDesign;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.AddTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.SequenceRepository;
import com.future.tailormade.repository.UserRepository;
import com.future.tailormade.utils.SequenceGenerator;
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
    private SequenceRepository sequenceRepository;

    @Override
    public Mono<AddOrEditTailorDesignResponse> execute(AddTailorDesignRequest request) {
        return findTailor(request.getTailorId())
                .flatMap(tailor -> addDesign(tailor, request))
                .flatMap(userDesignPair ->
                        addTailorDesign(userDesignPair.getFirst(), userDesignPair.getSecond()))
                .map(this::createResponse);
    }

    private Mono<Pair<User, Design>> addDesign(User tailor, AddTailorDesignRequest request) {
        return generateId(request.getTitle())
                .map(sequence -> createDesign(sequence, tailor, request))
                .flatMap(design -> designRepository.save(design))
                .map(design -> Pair.of(tailor, design));
    }

    private Mono<Design> addTailorDesign(User tailor, Design design) {
        TailorDesign tailorDesign = createTailorDesign(design);
        tailor.addDesign(tailorDesign);
        return userRepository.save(tailor).thenReturn(design);
    }

    private AddOrEditTailorDesignResponse createResponse(Design design) {
        AddOrEditTailorDesignResponse response = AddOrEditTailorDesignResponse.builder().build();
        BeanUtils.copyProperties(design, response);
        return response;
    }

    private Design createDesign(String id, User tailor, AddTailorDesignRequest request) {
        Design design = Design.builder()
                .id(id)
                .build();
        BeanUtils.copyProperties(request, design);
        design.setTailorName(tailor.getName());
        return design;
    }

    private Mono<Sequence> createSequence(String name) {
        return Mono.just(Sequence.builder()
                .name(name)
                .count(0L)
                .build());
    }

    private TailorDesign createTailorDesign(Design design) {
        TailorDesign tailorDesign = TailorDesign.builder().build();
        BeanUtils.copyProperties(design, tailorDesign);
        return tailorDesign;
    }

    private Mono<User> findTailor(String tailorId) {
        return userRepository.findByIdAndRole(tailorId, RoleEnum.ROLE_TAILOR);
    }

    private Mono<String> generateId(String title) {
        String name = SequenceGenerator.getId(SequenceGenerator.DESIGN, title);
        return sequenceRepository.findById(name)
                .switchIfEmpty(createSequence(name))
                .flatMap(this::saveSequence)
                .map(SequenceGenerator::generateSequence);
    }

    private Mono<Sequence> saveSequence(Sequence sequence) {
        sequence.setCount(sequence.getCount() + 1);
        return sequenceRepository.save(sequence);
    }
}