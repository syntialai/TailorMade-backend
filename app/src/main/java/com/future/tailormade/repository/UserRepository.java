package com.future.tailormade.repository;

import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Long> countAllByRole(RoleEnum role);

    Flux<User> findAllByRole(RoleEnum role, Pageable pageable);

    Flux<User> findAllByRoleAndNameStartsWith(RoleEnum role, String name, Pageable pageable);

    Mono<User> findByEmail(String email);

    Mono<User> findByIdAndRole(String id, RoleEnum role);

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByPhoneNumber(String phoneNumber);
}
