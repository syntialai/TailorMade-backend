package com.future.tailormade.command.wishlist.impl;

import com.future.tailormade.command.wishlist.GetWishlistByIdCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.payload.request.wishlist.GetWishlistByIdRequest;
import com.future.tailormade.payload.response.wishlist.GetWishlistByIdResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistDesignByIdResponse;
import com.future.tailormade.repository.WishlistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetWishlistByIdCommandImpl implements GetWishlistByIdCommand {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public Mono<GetWishlistByIdResponse> execute(GetWishlistByIdRequest request) {
        return findWishlist(request).map(this::createResponse);
    }

    private Mono<Wishlist> findWishlist(GetWishlistByIdRequest request) {
        return wishlistRepository.findByUserIdAndId(request.getUserId(), request.getId())
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private GetWishlistByIdResponse createResponse(Wishlist wishlist) {
        GetWishlistByIdResponse response = GetWishlistByIdResponse.builder().build();
        BeanUtils.copyProperties(wishlist, response);
        response.setDesign(createDesignResponse(wishlist.getDesign()));
        return response;
    }

    private GetWishlistDesignByIdResponse createDesignResponse(WishlistDesign wishlistDesign) {
        GetWishlistDesignByIdResponse response = GetWishlistDesignByIdResponse.builder().build();
        BeanUtils.copyProperties(wishlistDesign, response);
        return response;
    }
}
