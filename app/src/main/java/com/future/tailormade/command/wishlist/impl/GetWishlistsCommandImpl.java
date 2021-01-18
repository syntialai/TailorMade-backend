package com.future.tailormade.command.wishlist.impl;

import com.future.tailormade.command.wishlist.GetWishlistsCommand;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.payload.request.wishlist.GetWishlistsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.base.helper.ResponseHelper;
import com.future.tailormade.payload.response.wishlist.GetWishlistsDesignResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistsResponse;
import com.future.tailormade.repository.WishlistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetWishlistsCommandImpl implements GetWishlistsCommand {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public Mono<BasePagingResponse<GetWishlistsResponse>> execute(GetWishlistsRequest request) {
        return getAllWishlists(request)
                .map(this::createResponse)
                .collectList()
                .map(ResponseHelper::createPagingResponse)
                .flatMap(pagingResponse -> getWishlistsCount(request.getUserId(), pagingResponse));
    }

    private Flux<Wishlist> getAllWishlists(GetWishlistsRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getItemPerPage());
        return wishlistRepository.findAllByUserId(request.getUserId(), pageable)
                .switchIfEmpty(Flux.empty());
    }

    private Mono<BasePagingResponse<GetWishlistsResponse>> getWishlistsCount(
            String userId, BasePagingResponse<GetWishlistsResponse> pagingResponse) {
        return wishlistRepository.countAllByUserId(userId)
                .map(count -> ResponseHelper.setPagingResponseTotalItem(pagingResponse, count));
    }

    private GetWishlistsResponse createResponse(Wishlist wishlist) {
        GetWishlistsResponse response = GetWishlistsResponse.builder().build();
        BeanUtils.copyProperties(wishlist, response);
        response.setDesign(createDesignResponse(wishlist.getDesign()));
        return response;
    }

    private GetWishlistsDesignResponse createDesignResponse(WishlistDesign wishlistDesign) {
        GetWishlistsDesignResponse designResponse = GetWishlistsDesignResponse.builder().build();
        BeanUtils.copyProperties(wishlistDesign, designResponse);
        return designResponse;
    }
}
