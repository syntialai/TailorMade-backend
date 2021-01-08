package com.future.tailormade.command.wishlist;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.wishlist.GetWishlistByIdRequest;
import com.future.tailormade.payload.response.wishlist.GetWishlistByIdResponse;

public interface GetWishlistByIdCommand
        extends Command<GetWishlistByIdRequest, GetWishlistByIdResponse> {
}
