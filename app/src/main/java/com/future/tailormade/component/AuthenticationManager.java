package com.future.tailormade.component;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        try {
            if (!jwtTokenProvider.validateToken(authToken)) {
                return Mono.empty();
            }

            Claims claims = jwtTokenProvider.getAllClaimsFromToken(authToken);
            String role = claims.get("ROLE", String.class);
            GrantedAuthority authority = new SimpleGrantedAuthority(role);

            return Mono.just(
                    new UsernamePasswordAuthenticationToken(
                            claims.getSubject(),
                            null,
                            Collections.singletonList(authority)
                    )
            );
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
