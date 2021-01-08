package com.future.tailormade.payload.response.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWishlistDesignByIdResponse {

    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount;

    private String size;

//    TODO: Uncomment this when design api is merged
//    private SizeDetail sizeDetail;

    private String color;
}
