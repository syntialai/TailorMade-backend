package com.future.tailormade.service;

import com.future.tailormade.model.entity.base.Sequence;
import reactor.core.publisher.Mono;

public interface SequenceService {

    public Mono<String> generateId(String title, String type);

    public Mono<Sequence> saveSequence(Sequence sequence);
}
