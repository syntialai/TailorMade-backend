package com.future.tailormade.payload.request.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutWishlistMeasurementRequest {

    @NotBlank
    @Positive
    private Float chest;

    @NotBlank
    @Positive
    private Float waist;

    @NotBlank
    @Positive
    private Float hips;

    @NotBlank
    @Positive
    private Float neckToWaist;

    @NotBlank
    @Positive
    private Float inseam;
}
