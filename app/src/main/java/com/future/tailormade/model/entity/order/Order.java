package com.future.tailormade.model.entity.order;

import com.future.tailormade.constants.CollectionConstants;
import com.future.tailormade.model.entity.base.BaseEntity;
import com.future.tailormade.model.enums.OrderStatusEnum;
import io.github.classgraph.json.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = CollectionConstants.ORDER_COLLECTION)
public class Order extends BaseEntity {

    @Id
    private String id;

    private String userId;

    private String userName;

    private String tailorId;

    private String tailorName;

    private Integer quantity;

    private Double totalPrice;

    private Double totalDiscount;

    private OrderMeasurement measurement;

    private String specialInstructions;

    private OrderDesign design;

    private OrderStatusEnum status;
}
