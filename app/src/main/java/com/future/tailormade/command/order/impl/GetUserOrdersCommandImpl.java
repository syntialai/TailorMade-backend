package com.future.tailormade.command.order.impl;

import com.future.tailormade.command.order.GetUserOrdersCommand;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.entity.order.OrderDesign;
import com.future.tailormade.payload.request.order.GetUserOrdersRequest;
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
public class GetUserOrdersCommandImpl implements GetUserOrdersCommand {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Mono<BasePagingResponse<GetOrdersResponse>> execute(GetUserOrdersRequest request) {
        return getAllUserOrders(request)
                .map(this::createResponse)
                .collectList()
                .map(ResponseHelper::createPagingResponse)
                .flatMap(pagingResponse ->
                        getUserOrdersCount(request.getUserId(), pagingResponse));
    }

    private Flux<Order> getAllUserOrders(GetUserOrdersRequest request) {
        Pageable pageable = ResponseHelper
                .createPageable(request.getPage(), request.getItemPerPage());
        return orderRepository.findAllByUserId(request.getUserId(), pageable)
                .switchIfEmpty(Flux.empty());
    }

    private Mono<BasePagingResponse<GetOrdersResponse>> getUserOrdersCount(
            String userId, BasePagingResponse<GetOrdersResponse> pagingResponse) {
        return orderRepository.countAllByUserId(userId)
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
