package com.future.tailormade.command.design.impl;

import com.future.tailormade.command.design.GetDesignsCommand;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.payload.request.design.GetDesignsRequest;
import com.future.tailormade.payload.response.design.GetDesignsResponse;
import com.future.tailormade.repository.DesignRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetDesignsCommandImpl implements GetDesignsCommand {

    @Autowired
    private DesignRepository designRepository;

    @Override
    public Mono<List<GetDesignsResponse>> execute(GetDesignsRequest request) {
        return getAllDesign(request)
                .map(this::createResponse)
                .collectList();
    }

    private Flux<Design> getAllDesign(GetDesignsRequest request) {
        Pageable pageable = createPageable(request.getPage(), request.getItemPerPage());
        return designRepository
                .findAllByTitleIsLikeOrCategoryExists(request.getKeyword(), pageable);
    }

    private Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    private GetDesignsResponse createResponse(Design design) {
        GetDesignsResponse response = GetDesignsResponse.builder().build();
        BeanUtils.copyProperties(design, response);
        return response;
    }
}
