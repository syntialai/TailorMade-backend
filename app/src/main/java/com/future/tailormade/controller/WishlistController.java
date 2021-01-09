package com.future.tailormade.controller;

import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.wishlist.AddWishlistCommand;
import com.future.tailormade.command.wishlist.EditQuantityWishlistCommand;
import com.future.tailormade.command.wishlist.GetWishlistByIdCommand;
import com.future.tailormade.command.wishlist.GetWishlistsCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.wishlist.AddWishlistRequest;
import com.future.tailormade.payload.request.wishlist.EditQuantityWishlistRequest;
import com.future.tailormade.payload.request.wishlist.GetWishlistByIdRequest;
import com.future.tailormade.payload.request.wishlist.GetWishlistsRequest;
import com.future.tailormade.payload.response.wishlist.EditQuantityWishlistResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistByIdResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
public class WishlistController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(ApiPath.USERS_ID_WISHLISTS)
    public Mono<Response<List<GetWishlistsResponse>>> getUserWishlists(
            @PathVariable("userId") String userId,
            @RequestParam("page") int page,
            @RequestParam("itemPerPage") int itemPerPage
    ) {
        GetWishlistsRequest request = GetWishlistsRequest.builder()
                .userId(userId)
                .page(page)
                .itemPerPage(itemPerPage)
                .build();
        return commandExecutor.execute(GetWishlistsCommand.class, request)
                .map(getWishlistsResponse -> com.future.tailormade.payload.response.base.helper
                        .ResponseHelper.ok(
                                getWishlistsResponse.getData(),
                                page,
                                itemPerPage,
                                getWishlistsResponse.getTotalItem()
                        )
                )
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.USERS_ID_WISHLISTS_ID)
    public Mono<Response<GetWishlistByIdResponse>> getUserWishlistById(
            @PathVariable("userId") String userId,
            @PathVariable("id") String id
    ) {
        GetWishlistByIdRequest request = GetWishlistByIdRequest.builder()
                .userId(userId)
                .id(id)
                .build();
        return commandExecutor.execute(GetWishlistByIdCommand.class, request)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @PostMapping(value = ApiPath.USERS_ID_WISHLISTS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Object>> addUserWishlist(
            @PathVariable("userId") String userId,
            @RequestBody AddWishlistRequest request
    ) {
        request.setUserId(userId);
        return commandExecutor.execute(AddWishlistCommand.class, request)
                .thenReturn(com.future.tailormade.payload.response.base.helper
                        .ResponseHelper.created())
                .subscribeOn(Schedulers.elastic());
    }

    @PutMapping(value = ApiPath.USERS_ID_WISHLISTS_ID_EDIT_QUANTITY,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<EditQuantityWishlistResponse>> editUserWishlistQuantity(
            @PathVariable("userId") String userId,
            @PathVariable("id") String id,
            @RequestBody EditQuantityWishlistRequest request
    ) {
        request.setWishlistId(id);
        request.setUserId(userId);
        return commandExecutor.execute(EditQuantityWishlistCommand.class, request)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }
}
