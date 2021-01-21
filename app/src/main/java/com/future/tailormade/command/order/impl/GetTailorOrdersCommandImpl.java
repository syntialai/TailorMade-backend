package com.future.tailormade.command.order.impl;

import com.future.tailormade.command.order.GetTailorOrdersCommand;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.entity.order.OrderDesign;
import com.future.tailormade.model.enums.OrderStatusEnum;
import com.future.tailormade.payload.request.order.GetTailorOrdersRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.base.helper.ResponseHelper;
import com.future.tailormade.payload.response.order.GetOrdersDesignResponse;
import com.future.tailormade.payload.response.order.GetOrdersResponse;
import com.future.tailormade.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetTailorOrdersCommandImpl implements GetTailorOrdersCommand {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Mono<BasePagingResponse<GetOrdersResponse>> execute(GetTailorOrdersRequest request) {
        return getAllTailorOrders(request)
                .map(this::createResponse)
                .collectList()
                .map(ResponseHelper::createPagingResponse)
                .flatMap(pagingResponse ->
                        getTailorOrdersCount(
                                request.getTailorId(),
                                request.getStatus(),
                                pagingResponse
                        )
                );
    }

    private Flux<Order> getAllTailorOrders(GetTailorOrdersRequest request) {
        Pageable pageable = ResponseHelper
                .createPageable(request.getPage(), request.getItemPerPage());
        return orderRepository
                .findAllByTailorIdAndStatus(request.getTailorId(), request.getStatus(), pageable)
                .switchIfEmpty(Flux.empty());
    }

    private Mono<BasePagingResponse<GetOrdersResponse>> getTailorOrdersCount(
            String tailorId,
            OrderStatusEnum status,
            BasePagingResponse<GetOrdersResponse> pagingResponse
    ) {
        return orderRepository.countAllByTailorIdAndStatus(tailorId, status)
                .map(count -> ResponseHelper.setPagingResponseTotalItem(pagingResponse, count));
    }

    private GetOrdersResponse createResponse(Order order) {
        GetOrdersResponse response = GetOrdersResponse.builder().build();
        BeanUtils.copyProperties(order, response);
        response.setDesign(createDesignResponse(order.getDesign()));
        return response;
    }

    private GetOrdersDesignResponse createDesignResponse(OrderDesign orderDesign) {
        GetOrdersDesignResponse designResponse = GetOrdersDesignResponse.builder().build();
        BeanUtils.copyProperties(orderDesign, designResponse);
        return designResponse;
    }
}
