package com.future.tailormade.repository;

import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.enums.OrderStatusEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

    Mono<Integer> countAllByTailorIdAndStatus(String tailorId, OrderStatusEnum status);

    Mono<Integer> countAllByTailorIdAndStatusIsNot(String tailorId, OrderStatusEnum status);

    Mono<Integer> countAllByUserId(String userId);

    @Query("{ id: { $exists: true }}")
    Flux<Order> findAllByUserId(String userId, Pageable pageable);

    @Query("{ id: { $exists: true }}")
    Flux<Order> findAllByTailorIdAndStatus(
            String tailorId, OrderStatusEnum status, Pageable pageable);

    @Query("{ id: { $exists: true }}")
    Flux<Order> findAllByTailorIdAndStatusIsNot(String tailorId, OrderStatusEnum status);

    Mono<Order> findByUserIdAndId(String userId, String id);

    Mono<Order> findByTailorIdAndId(String tailorId, String id);

    Mono<Order> findByTailorIdAndIdAndStatus(String tailorId, String id, OrderStatusEnum status);
}
