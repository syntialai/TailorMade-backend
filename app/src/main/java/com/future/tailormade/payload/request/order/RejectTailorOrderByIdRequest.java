package com.future.tailormade.payload.request.order;

import com.future.tailormade.model.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RejectTailorOrderByIdRequest {

    private String id;

    private String tailorId;

    private OrderStatusEnum status = OrderStatusEnum.Rejected;
}
