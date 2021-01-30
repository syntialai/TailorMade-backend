package com.future.tailormade.payload.request.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutWishlistRequest {

    private String userId;

    private String wishlistId;

    private String specialInstructions;

    private CheckoutWishlistMeasurementRequest measurements;
}
