package com.future.tailormade.model.entity.wishlist;

import com.future.tailormade.model.entity.design.SizeDetail;
import io.github.classgraph.json.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDesign {

    @Id
    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount = 0.0;

    private String size;

    private SizeDetail sizeDetail;

    private String color;

    private String tailorId;

    private String tailorName;

    private boolean active = true;
}
