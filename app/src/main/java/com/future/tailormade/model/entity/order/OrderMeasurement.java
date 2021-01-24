package com.future.tailormade.model.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMeasurement {

    private Float chest;

    private Float waist;

    private Float hips;

    private Float neckToWaist;

    private Float inseam;
}
