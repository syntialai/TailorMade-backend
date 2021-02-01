package com.future.tailormade.command.wishlist;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.wishlist.AddWishlistRequest;
import com.future.tailormade.payload.response.wishlist.AddWishlistResponse;

public interface AddWishlistCommand extends Command<AddWishlistRequest, AddWishlistResponse> {
}
