package com.future.tailormade.repository;

import com.future.tailormade.model.entity.wishlist.Wishlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends ReactiveMongoRepository<Wishlist, String> {
}
