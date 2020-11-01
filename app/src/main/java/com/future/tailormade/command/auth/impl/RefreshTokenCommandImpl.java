package com.future.tailormade.command.auth.impl;

import com.future.tailormade.command.auth.RefreshTokenCommand;
import com.future.tailormade.model.entity.auth.Token;
import com.future.tailormade.payload.request.auth.RefreshTokenRequest;
import com.future.tailormade.payload.response.auth.TokenResponse;
import reactor.core.publisher.Mono;

public class RefreshTokenCommandImpl implements RefreshTokenCommand {

    @Override
    public Mono<TokenResponse> execute(RefreshTokenRequest request) {
        return null;
    }

    private Mono<TokenResponse> refreshToken(RefreshTokenRequest request) {
        // Call token helper and create response
        return null;
    }

    private TokenResponse createResponse(Token token) {
        return TokenResponse.builder()
                .token(token)
                .build();
    }
}
