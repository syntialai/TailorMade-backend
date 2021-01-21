package com.future.tailormade.payload.response.wishlist;

import com.future.tailormade.model.entity.design.SizeDetail;
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

    private SizeDetail sizeDetail;

    private String color;
}
