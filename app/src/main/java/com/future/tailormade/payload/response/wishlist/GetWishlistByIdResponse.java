package com.future.tailormade.payload.response.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWishlistByIdResponse {

    private String id;

    private String createdAt;

    private String updatedAt;

    private String userId;

    private String userName;

    private String tailorId;

    private String tailorName;

    private Integer quantity;

    private GetWishlistDesignByIdResponse design;
}
