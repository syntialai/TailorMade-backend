package com.future.tailormade.command.auth.impl;

import com.future.tailormade.command.auth.RefreshTokenCommand;
import com.future.tailormade.component.JwtTokenProvider;
import com.future.tailormade.model.entity.auth.Token;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.auth.RefreshTokenRequest;
import com.future.tailormade.payload.response.auth.TokenResponse;
import com.future.tailormade.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RefreshTokenCommandImpl implements RefreshTokenCommand {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<TokenResponse> execute(RefreshTokenRequest request) {
        return getUserById(request.getRefreshToken()).map(user -> {
            Token token = getToken(
                    jwtTokenProvider.generateAccessToken(user),
                    jwtTokenProvider.generateRefreshToken(user)
            );
            return createResponse(token);
        }).onErrorReturn(createResponse(null));
    }

    private Mono<User> getUserById(String token) {
        String userId = jwtTokenProvider.getUserIdFromToken(token);
        return userRepository.findById(userId);
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
