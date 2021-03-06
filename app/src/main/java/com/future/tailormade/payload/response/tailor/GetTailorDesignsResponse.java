package com.future.tailormade.payload.response.tailor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTailorDesignsResponse {

    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount;

    private boolean active;
}
