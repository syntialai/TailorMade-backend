package com.future.tailormade.model.entity.order;

import io.github.classgraph.json.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDesign {

    @Id
    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount = 0.0;

    private String size;

    private String color;

    private String tailorId;

    private String tailorName;
}
