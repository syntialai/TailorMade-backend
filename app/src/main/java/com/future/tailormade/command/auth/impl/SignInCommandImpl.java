package com.future.tailormade.command.auth.impl;

import com.future.tailormade.command.auth.SignInCommand;
import com.future.tailormade.model.entity.auth.Token;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.auth.SignInRequest;
import com.future.tailormade.payload.response.auth.TokenResponse;
import com.future.tailormade.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SignInCommandImpl implements SignInCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<TokenResponse> execute(SignInRequest request) {
        return findByUsername(request.getUsername()).map(user -> {
            // Check user password and create response
        });
    }

    private Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private TokenResponse createResponse(Token token) {
        return TokenResponse.builder()
                .token(token)
                .build();
    }
}
