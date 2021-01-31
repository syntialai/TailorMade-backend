package com.future.tailormade.service.impl;

import com.future.tailormade.model.entity.base.Sequence;
import com.future.tailormade.repository.SequenceRepository;
import com.future.tailormade.service.SequenceService;
import com.future.tailormade.utils.SequenceGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceRepository sequenceRepository;

    @Override
    public Mono<String> generateId(String title, String type) {
        String id = SequenceGeneratorUtil.getId(type, title);
        return sequenceRepository.findById(id)
                .switchIfEmpty(createSequence(id))
                .flatMap(this::saveSequence)
                .map(SequenceGeneratorUtil::generateSequence);
    }

    private Mono<Sequence> saveSequence(Sequence sequence) {
        sequence.setCount(sequence.getCount() + 1);
        return sequenceRepository.save(sequence);
    }

    private Mono<Sequence> createSequence(String id) {
        return Mono.just(Sequence.builder()
                .id(id)
                .count(0L)
                .build());
    }
}
