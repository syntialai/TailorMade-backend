package com.future.tailormade.payload.request.tailor;

import com.future.tailormade.model.entity.design.Color;
import com.future.tailormade.model.entity.design.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditTailorDesignRequest {

    private String id;

    private String tailorId;

    @NotBlank
    private String title;

    @NotBlank
    private String image;

    @Positive
    private Double price;

    @PositiveOrZero
    private Double discount = 0.0;

    private List<Size> size;

    private List<Color> color;

    @NotBlank
    private String description;

    private List<String> category = Collections.emptyList();
}
