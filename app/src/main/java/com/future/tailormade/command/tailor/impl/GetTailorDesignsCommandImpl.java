package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.GetTailorDesignsCommand;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.payload.request.tailor.GetTailorDesignsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.base.helper.ResponseHelper;
import com.future.tailormade.payload.response.tailor.GetTailorDesignsResponse;
import com.future.tailormade.repository.DesignRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetTailorDesignsCommandImpl implements GetTailorDesignsCommand {

    @Autowired
    private DesignRepository designRepository;

    @Override
    public Mono<BasePagingResponse<GetTailorDesignsResponse>> execute(
            GetTailorDesignsRequest request)
    {
        return getTailorDesigns(request)
                .map(this::createResponse)
                .collectList()
                .map(ResponseHelper::createPagingResponse)
                .flatMap(pagingResponse -> 
                        getTailorDesignsCount(request.getTailorId(), pagingResponse));
    }

    private Flux<Design> getTailorDesigns(GetTailorDesignsRequest request) {
        Pageable pageable = ResponseHelper
                .createPageable(request.getPage(), request.getItemPerPage());
        return designRepository.findAllByTailorId(request.getTailorId(), pageable)
                .switchIfEmpty(Flux.empty());
    }

    private Mono<BasePagingResponse<GetTailorDesignsResponse>> getTailorDesignsCount(
            String tailorId, BasePagingResponse<GetTailorDesignsResponse> pagingResponse) {
        return designRepository.countAllByTailorId(tailorId)
                .map(count -> ResponseHelper.setPagingResponseTotalItem(pagingResponse, count));
    }

    private GetTailorDesignsResponse createResponse(Design design) {
        GetTailorDesignsResponse response = GetTailorDesignsResponse.builder().build();
        BeanUtils.copyProperties(design, response);
        return response;
    }
}
