package com.future.tailormade.service;

import reactor.core.publisher.Mono;

public interface SequenceService {

    Mono<String> generateId(String title, String type);
}
