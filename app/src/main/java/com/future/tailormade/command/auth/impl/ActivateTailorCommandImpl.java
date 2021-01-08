package com.future.tailormade.command.auth.impl;

import com.future.tailormade.command.auth.ActivateTailorCommand;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.response.auth.ActivateTailorResponse;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ActivateTailorCommandImpl implements ActivateTailorCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<ActivateTailorResponse> execute(String request) {
        return findById(request)
                .flatMap(user -> {
                    user.setRole(RoleEnum.ROLE_TAILOR);
                    return userRepository.save(user);
                })
                .map(user -> createResponse(user.getRole()));
    }

    private Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    private ActivateTailorResponse createResponse(RoleEnum role) {
        return ActivateTailorResponse.builder()
                .role(role)
                .build();
    }
}
