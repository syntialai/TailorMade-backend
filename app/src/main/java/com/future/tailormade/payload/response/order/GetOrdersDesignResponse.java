package com.future.tailormade.payload.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersDesignResponse {

    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount = 0.0;

    private String size;

    private String color;
}
