package com.future.tailormade.command.wishlist;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.wishlist.EditQuantityWishlistRequest;
import com.future.tailormade.payload.response.wishlist.EditQuantityWishlistResponse;

public interface EditQuantityWishlistCommand
        extends Command<EditQuantityWishlistRequest, EditQuantityWishlistResponse> {
}
