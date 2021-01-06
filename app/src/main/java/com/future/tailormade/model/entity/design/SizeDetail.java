package com.future.tailormade.model.entity.design;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizeDetail {

    private Float chest;

    private Float waist;

    private Float hips;

    private Float neckToWaist;

    private Float inseam;
}
