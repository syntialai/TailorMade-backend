package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.AddTailorDesignCommand;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.model.entity.user.TailorDesign;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.AddTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AddTailorDesignCommandImpl implements AddTailorDesignCommand {

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<AddOrEditTailorDesignResponse> execute(AddTailorDesignRequest request) {
        return null;
    }

    private Mono<Design> addDesign(User tailor, AddTailorDesignRequest request) {
        Design design = createDesign(tailor, request);
        return designRepository.save(design);
    }

    private Mono<User> addTailorDesign(User tailor, Design design) {
        TailorDesign tailorDesign = createTailorDesign(design);
        tailor.addDesign(tailorDesign);
        return userRepository.save(tailor);
    }

    private Mono<User> findTailor(String tailorId) {
        return userRepository.findByIdAndRole(tailorId, RoleEnum.ROLE_TAILOR);
    }

    private Design createDesign(User tailor, AddTailorDesignRequest request) {
        Design design = Design.builder()
                .id(UUID.randomUUID().toString())
                .build();
        BeanUtils.copyProperties(request, design);
        design.setTailorName(tailor.getName());
        return design;
    }

    private TailorDesign createTailorDesign(Design design) {
        TailorDesign tailorDesign = TailorDesign.builder().build();
        BeanUtils.copyProperties(design, tailorDesign);
        return tailorDesign;
    }
}
