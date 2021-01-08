package com.future.tailormade.repository;

import com.future.tailormade.model.entity.wishlist.Wishlist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WishlistRepository extends ReactiveMongoRepository<Wishlist, String> {

    Mono<Integer> countAllByUserId(String userId);

    @Query("{ id: { $exists: true }}")
    Flux<Wishlist> findAllByUserId(String userId, Pageable pageable);

    Mono<Wishlist> findByUserIdAndId(String userId, String id);
}
