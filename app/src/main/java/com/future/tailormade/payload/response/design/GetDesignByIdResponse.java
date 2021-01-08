package com.future.tailormade.payload.response.design;

import com.future.tailormade.model.entity.design.Color;
import com.future.tailormade.model.entity.design.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDesignByIdResponse {

    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount;

    private List<Size> size;

    private List<Color> color;

    private String description;

    private List<String> category;

    private String tailorId;

    private String tailorName;

    private boolean active;
}
