package com.future.tailormade.payload.response.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWishlistsDesignResponse {

    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount;

    private String size;

    private String color;
}
