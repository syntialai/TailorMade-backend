package com.future.tailormade.payload.request.tailor;

import com.future.tailormade.constants.BaseConstants;
import com.future.tailormade.model.entity.design.Color;
import com.future.tailormade.model.entity.design.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(BaseConstants.MIN_DESIGN_TITLE_COUNT)
    @Max(BaseConstants.MAX_DESIGN_TITLE_COUNT)
    private String title;

    @NotBlank
    private String image;

    @Positive
    private Double price;

    @PositiveOrZero
    private Double discount = 0.0;

    @NotBlank
    private List<Size> size;

    @NotBlank
    private List<Color> color;

    @NotBlank
    @Min(BaseConstants.MIN_DESIGN_DESCRIPTION_COUNT)
    @Max(BaseConstants.MAX_DESIGN_DESCRIPTION_COUNT)
    private String description;

    private List<String> category = Collections.emptyList();
}
