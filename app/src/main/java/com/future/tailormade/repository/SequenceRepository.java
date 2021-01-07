package com.future.tailormade.repository;

import com.future.tailormade.model.entity.base.Sequence;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SequenceRepository extends ReactiveMongoRepository<Sequence, String> {
}
