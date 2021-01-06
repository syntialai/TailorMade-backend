package com.future.tailormade.repository;

import com.future.tailormade.model.entity.design.Design;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DesignRepository extends ReactiveMongoRepository<Design, String> {

    Flux<Design> findAllByTailorId(String tailorId, Pageable pageable);

    Flux<Design> findAllByTitleIsLikeOrCategoryExists(String keyword, Pageable pageable);
}
