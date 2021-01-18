package com.future.tailormade.command.order.impl;

import com.future.tailormade.command.order.GetUserOrderByIdCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.payload.request.order.GetUserOrderByIdRequest;
import com.future.tailormade.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetUserOrderByIdCommandImpl implements GetUserOrderByIdCommand {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Mono<Order> execute(GetUserOrderByIdRequest request) {
        return orderRepository.findByUserIdAndId(request.getUserId(), request.getId())
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }
}
