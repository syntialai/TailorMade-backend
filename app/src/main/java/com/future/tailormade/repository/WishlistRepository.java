package com.future.tailormade.repository;

import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WishlistRepository extends ReactiveMongoRepository<Wishlist, String> {

    Mono<Long> countAllByUserId(String userId);

    Flux<Wishlist> findAllByUserId(String userId, Pageable pageable);

    Mono<Wishlist> findByUserIdAndId(String userId, String id);

    Mono<Wishlist> findByUserIdAndDesign(String userId, WishlistDesign design);
}
