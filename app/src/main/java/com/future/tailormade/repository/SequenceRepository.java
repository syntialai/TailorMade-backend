package com.future.tailormade.repository;

import com.future.tailormade.model.entity.base.Sequence;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SequenceRepository extends ReactiveMongoRepository<Sequence, String> {

    Mono<Sequence> findByName(String name);
}
