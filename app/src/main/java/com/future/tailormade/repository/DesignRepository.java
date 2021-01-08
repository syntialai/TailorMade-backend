package com.future.tailormade.repository;

import com.future.tailormade.model.entity.design.Design;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DesignRepository extends ReactiveMongoRepository<Design, String> {

    @Query("{ id: { $exists: true }}")
    Flux<Design> findAllByTailorId(String tailorId, Pageable pageable);

    @Query("{ id: { $exists: true }}")
    Flux<Design> findAllByTitleIsLikeOrCategoryExists(String keyword, Pageable pageable);

    Mono<Integer> countAllByTitleIsLikeOrCategoryExists(String keyword);
}
