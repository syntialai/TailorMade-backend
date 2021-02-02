package com.future.tailormade.command.wishlist.impl;

import com.future.tailormade.command.wishlist.DeleteWishlistCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.payload.request.wishlist.DeleteWishlistRequest;
import com.future.tailormade.payload.response.wishlist.DeleteWishlistResponse;
import com.future.tailormade.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteWishlistCommandImpl implements DeleteWishlistCommand {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public Mono<DeleteWishlistResponse> execute(DeleteWishlistRequest request) {
        return findWishlist(request)
                .flatMap(this::deleteWishlist)
                .thenReturn(createResponse(request.getId()));
    }

    private DeleteWishlistResponse createResponse(String wishlistId) {
        return DeleteWishlistResponse.builder().wishlistId(wishlistId).build();
    }

    private Mono<Wishlist> findWishlist(DeleteWishlistRequest request) {
        return wishlistRepository.findByUserIdAndId(request.getUserId(), request.getId())
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private Mono<Void> deleteWishlist(Wishlist wishlist) {
        return wishlistRepository.deleteById(wishlist.getId());
    }
}
