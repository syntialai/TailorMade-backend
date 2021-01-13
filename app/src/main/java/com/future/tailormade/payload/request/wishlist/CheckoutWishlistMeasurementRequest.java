package com.future.tailormade.payload.request.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutWishlistMeasurementRequest {

    @Positive
    private Float chest;

    @Positive
    private Float waist;

    @Positive
    private Float hips;

    @Positive
    private Float neckToWaist;

    @Positive
    private Float inseam;
}
