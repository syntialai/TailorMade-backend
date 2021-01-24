package com.future.tailormade.command.wishlist;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.wishlist.GetWishlistsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistsResponse;

public interface GetWishlistsCommand
        extends Command<GetWishlistsRequest, BasePagingResponse<GetWishlistsResponse>> {
}
