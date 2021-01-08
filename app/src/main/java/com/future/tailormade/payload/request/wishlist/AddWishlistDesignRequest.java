package com.future.tailormade.payload.request.wishlist;

import com.future.tailormade.constants.BaseConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddWishlistDesignRequest {

    @NotBlank
    private String id;

    @NotBlank
    @Min(BaseConstants.MIN_DESIGN_TITLE_COUNT)
    @Max(BaseConstants.MAX_DESIGN_TITLE_COUNT)
    private String title;

    @NotBlank
    private String image;

    @NotBlank
    @Positive
    private Double price;

    @NotBlank
    @PositiveOrZero
    private Double discount;

    @NotBlank
    private String size;

//    TODO: Uncomment this when design api is merged
//    @NotBlank
//    private SizeDetail sizeDetail;

    @NotBlank
    private String color;
}
