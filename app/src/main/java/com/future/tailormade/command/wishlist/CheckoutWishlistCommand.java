package com.future.tailormade.command.wishlist;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.wishlist.CheckoutWishlistRequest;
import com.future.tailormade.payload.response.wishlist.CheckoutWishlistResponse;

public interface CheckoutWishlistCommand
        extends Command<CheckoutWishlistRequest, CheckoutWishlistResponse> {
}
