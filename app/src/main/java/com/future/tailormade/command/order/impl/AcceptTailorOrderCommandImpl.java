package com.future.tailormade.command.order.impl;

import com.future.tailormade.command.order.AcceptTailorOrderCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.enums.OrderStatusEnum;
import com.future.tailormade.payload.request.order.AcceptTailorOrderRequest;
import com.future.tailormade.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AcceptTailorOrderCommandImpl implements AcceptTailorOrderCommand {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Mono<Void> execute(AcceptTailorOrderRequest request) {
        return findOrder(request)
                .flatMap(this::acceptOrder)
                .then();
    }

    private Mono<Order> findOrder(AcceptTailorOrderRequest request) {
        return orderRepository.findByTailorIdAndIdAndStatus(
                request.getTailorId(), request.getId(), OrderStatusEnum.Incoming)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private Mono<Order> acceptOrder(Order order) {
        order.setStatus(OrderStatusEnum.Accepted);
        return orderRepository.save(order);
    }
}
