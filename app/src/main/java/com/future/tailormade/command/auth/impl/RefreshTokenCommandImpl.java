package com.future.tailormade.command.auth.impl;

import com.future.tailormade.command.auth.RefreshTokenCommand;
import com.future.tailormade.model.entity.auth.Token;
import com.future.tailormade.payload.request.auth.RefreshTokenRequest;
import com.future.tailormade.payload.response.auth.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class RefreshTokenCommandImpl implements RefreshTokenCommand {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<TokenResponse> execute(RefreshTokenRequest request) {
        Token token = getToken(
                jwtTokenProvider.generateAccessToken(user),
                jwtTokenProvider.generateRefreshToken(user)
        );
        return Mono.justOrEmpty(createResponse(token));
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
