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
public class AddWishlistRequest {

    private String userId;

    @NotBlank
    private String userName;

    @NotBlank
    private String tailorId;

    @NotBlank
    private String tailorName;

    @NotBlank
    @Positive
    private Integer quantity;

    @NotBlank
    private AddWishlistDesignRequest design;
}
