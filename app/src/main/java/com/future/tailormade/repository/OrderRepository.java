package com.future.tailormade.repository;

import com.future.tailormade.model.entity.order.Order;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

    @Query("{ id: { $exists: true }}")
    Flux<Order> findAllByUserId(String userId, Pageable pageable);

    @Query("{ id: { $exists: true }}")
    Flux<Order> findAllByTailorIdAndStatus(String tailorId, String status, Pageable pageable);

    Mono<Order> findByUserIdAndAndId(String userId, String id);

    Mono<Order> findByTailorIdAndAndId(String userId, String id);
}
