package com.future.tailormade.command.wishlist.impl;

import com.future.tailormade.command.wishlist.EditQuantityWishlistCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.payload.request.wishlist.EditQuantityWishlistRequest;
import com.future.tailormade.payload.response.wishlist.EditQuantityWishlistResponse;
import com.future.tailormade.repository.WishlistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EditQuantityWishlistCommandImpl implements EditQuantityWishlistCommand {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public Mono<EditQuantityWishlistResponse> execute(EditQuantityWishlistRequest request) {
        return findWishlist(request)
                .flatMap(wishlist -> saveWishlist(wishlist, request.getQuantity()))
                .map(this::createResponse);
    }

    private EditQuantityWishlistResponse createResponse(Wishlist wishlist) {
        EditQuantityWishlistResponse response = EditQuantityWishlistResponse.builder().build();
        BeanUtils.copyProperties(wishlist, response);
        return response;
    }

    private Mono<Wishlist> findWishlist(EditQuantityWishlistRequest request) {
        return wishlistRepository.findByUserIdAndId(request.getUserId(), request.getWishlistId())
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private Mono<Wishlist> saveWishlist(Wishlist wishlist, Integer quantity) {
        wishlist.setQuantity(quantity);
        return wishlistRepository.save(wishlist);
    }
}
