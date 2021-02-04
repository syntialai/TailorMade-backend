package com.future.tailormade.command.order.impl;

import com.future.tailormade.command.order.RejectTailorOrderCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.enums.OrderStatusEnum;
import com.future.tailormade.payload.request.order.RejectTailorOrderRequest;
import com.future.tailormade.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RejectTailorOrderCommandImpl implements RejectTailorOrderCommand {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Mono<Void> execute(RejectTailorOrderRequest request) {
        return findOrder(request)
                .flatMap(this::rejectOrder)
                .then();
    }

    private Mono<Order> findOrder(RejectTailorOrderRequest request) {
        return orderRepository.findByTailorIdAndIdAndStatusOrderByCreatedAtDesc(
                request.getTailorId(), request.getId(), OrderStatusEnum.Incoming)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private Mono<Order> rejectOrder(Order order) {
        order.setStatus(OrderStatusEnum.Rejected);
        return orderRepository.save(order);
    }
}
