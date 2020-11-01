package com.future.tailormade.command.auth.impl;

import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.auth.SignUpCommand;
import com.future.tailormade.model.entity.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.auth.SignUpRequest;
import com.future.tailormade.repository.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class SignUpCommandImpl implements SignUpCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<Response> execute(SignUpRequest request) {
        return Mono.fromCallable(() -> createUser(request))
                .flatMap(user -> userRepository.save(user))
                .map(user -> ResponseHelper.ok());
    }

    private User createUser(SignUpRequest signUpRequest) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .build();
        BeanUtils.copyProperties(signUpRequest, user);

        // Encode password to save

        user.setRole(RoleEnum.ROLE_USER);

        return user;
    }
}
