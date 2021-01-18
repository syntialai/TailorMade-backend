package com.future.tailormade.payload.request.wishlist;

import com.future.tailormade.constants.BaseConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutWishlistRequest {

    private String wishlistId;

    @Max(BaseConstants.MAX_SPECIAL_INSTRUCTIONS_COUNT)
    private String specialInstructions;

    private CheckoutWishlistMeasurementRequest measurements;
}
