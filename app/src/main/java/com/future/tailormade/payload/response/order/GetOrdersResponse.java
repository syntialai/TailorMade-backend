package com.future.tailormade.payload.response.order;

import com.future.tailormade.model.enums.OrderStatusEnum;
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

    private Long createdAt;

    private Long updatedAt;

    private String userId;

    private String tailorId;

    private Integer quantity;

    private Double totalPrice;

    private Double totalDiscount;

    private OrderStatusEnum status;

    private GetOrdersDesignResponse design;
}
