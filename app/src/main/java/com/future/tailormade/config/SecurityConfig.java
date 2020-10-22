package com.future.tailormade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class SecurityConfig {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((serverWebExchange, e) -> Mono.fromRunnable(() ->
                        serverWebExchange
                                .getResponse()
                                .setStatusCode(HttpStatus.UNAUTHORIZED)))
                .accessDeniedHandler((serverWebExchange, e) -> Mono.fromRunnable(() ->
                        serverWebExchange
                                .getResponse()
                                .setStatusCode(HttpStatus.FORBIDDEN)))
                .and()
//                .authenticationManager(authenticationManager)
//                .securityContextRepository(securityContextRepository)
                .headers().frameOptions().disable()
                .and()
                .authorizeExchange()
                .pathMatchers(ApiPath.AUTH_LOGIN).permitAll()
                .anyExchange().authenticated()
                .and().build();
    }
}
