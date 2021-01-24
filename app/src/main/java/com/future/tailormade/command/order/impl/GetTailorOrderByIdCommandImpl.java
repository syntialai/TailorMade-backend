package com.future.tailormade.command.order.impl;

import com.future.tailormade.command.order.GetTailorOrderByIdCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.payload.request.order.GetTailorOrderByIdRequest;
import com.future.tailormade.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetTailorOrderByIdCommandImpl implements GetTailorOrderByIdCommand {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Mono<Order> execute(GetTailorOrderByIdRequest request) {
        return orderRepository.findByTailorIdAndId(request.getTailorId(), request.getId())
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }
}
