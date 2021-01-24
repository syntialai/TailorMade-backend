package com.future.tailormade.service;

import com.future.tailormade.model.entity.base.Sequence;
import reactor.core.publisher.Mono;

public interface SequenceService {

    Mono<String> generateId(String title, String type);

    Mono<Sequence> saveSequence(Sequence sequence);
}
