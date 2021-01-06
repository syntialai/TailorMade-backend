package com.future.tailormade.repository;

import com.future.tailormade.model.entity.design.Design;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DesignRepository extends ReactiveMongoRepository<Design, String> {

    Page<Mono<Design>> findAllByTailorId(String tailorId, Pageable pageable);

    Page<Mono<Design>> findAllByTitleIsLikeOrCategoryExists(String keyword, Pageable pageable);
}
