package com.future.tailormade.command.auth.impl;

import com.future.tailormade.command.auth.SignInCommand;
import com.future.tailormade.component.CustomPasswordEncoder;
import com.future.tailormade.component.JwtTokenProvider;
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

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<TokenResponse> execute(SignInRequest request) {
        return findByUsername(request.getUsername()).map(user -> {
            if (passwordEncoder.encode(
                    request.getUsername()).equals(user.getPassword())
            ) {
                Token token = getToken(
                        jwtTokenProvider.generateAccessToken(user),
                        jwtTokenProvider.generateRefreshToken(user)
                );
                return createResponse(token);
            } else {
                return createResponse(null);
            }
        }).onErrorReturn(createResponse(null));
    }

    private Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Token getToken(String access, String refresh) {
        return Token.builder()
                .access(access)
                .refresh(refresh)
                .build();
    }

    private TokenResponse createResponse(Token token) {
        return TokenResponse.builder()
                .token(token)
                .build();
    }
}
