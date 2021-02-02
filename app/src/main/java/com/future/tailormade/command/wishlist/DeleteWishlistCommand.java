package com.future.tailormade.command.wishlist;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.wishlist.DeleteWishlistRequest;
import com.future.tailormade.payload.response.wishlist.DeleteWishlistResponse;

public interface DeleteWishlistCommand
        extends Command<DeleteWishlistRequest, DeleteWishlistResponse> {
}
