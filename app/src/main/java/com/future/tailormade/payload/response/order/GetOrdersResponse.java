package com.future.tailormade.payload.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersResponse {

    private String id;

    private String createdAt;

    private String updatedAt;

    private String userId;

    private String tailorId;

    private Integer quantity;

    private Double totalPrice;

    private Double totalDiscount;

    private GetOrdersDesignResponse design;
}
