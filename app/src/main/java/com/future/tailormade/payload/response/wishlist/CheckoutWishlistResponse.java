package com.future.tailormade.payload.response.wishlist;

import com.future.tailormade.model.entity.order.OrderDesign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutWishlistResponse {

    private String id;

    private String userId;

    private String tailorId;

    private Integer quantity;

    private Double totalPrice;

    private Double totalDiscount;

    private OrderDesign design;
}
