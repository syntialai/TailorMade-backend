package com.future.tailormade.command.design.impl;

import com.future.tailormade.command.design.GetDesignByIdCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.payload.response.design.GetDesignByIdResponse;
import com.future.tailormade.repository.DesignRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetDesignByIdCommandImpl implements GetDesignByIdCommand {

    @Autowired
    private DesignRepository designRepository;

    @Override
    public Mono<GetDesignByIdResponse> execute(String request) {
        return findById(request).map(this::createResponse);
    }

    private Mono<Design> findById(String id) {
        return designRepository.findById(id)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private GetDesignByIdResponse createResponse(Design design) {
        GetDesignByIdResponse response = GetDesignByIdResponse.builder().build();
        BeanUtils.copyProperties(design, response);
        return response;
    }
}
