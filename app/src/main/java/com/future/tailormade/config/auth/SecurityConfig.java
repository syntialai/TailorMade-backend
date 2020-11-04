package com.future.tailormade.config.auth;

import com.future.tailormade.component.AuthenticationManager;
import com.future.tailormade.component.SecurityContextRepository;
import com.future.tailormade.constants.ApiPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    private static final String[] AUTH_LIST = {
            ApiPath.USERS_SIGN_IN,
            ApiPath.USERS_SIGN_UP
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint((serverWebExchange, e) ->
                        Mono.fromRunnable(() ->
                                serverWebExchange
                                        .getResponse()
                                        .setStatusCode(HttpStatus.UNAUTHORIZED)
                        ))
                .accessDeniedHandler((serverWebExchange, e) ->
                        Mono.fromRunnable(() ->
                                serverWebExchange
                                        .getResponse()
                                        .setStatusCode(HttpStatus.FORBIDDEN)
                        ))
                .and()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .headers().frameOptions().disable()
                .and()
                .authorizeExchange()
                .pathMatchers(AUTH_LIST).permitAll()
                .anyExchange().authenticated()
                .and().build();
    }
}
