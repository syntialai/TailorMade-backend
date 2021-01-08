package com.future.tailormade.model.entity.wishlist;

import com.future.tailormade.model.entity.design.Color;
import com.future.tailormade.model.entity.design.Size;
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

    private Size size;

    private Color color;

    private String tailorId;

    private String tailorName;

    private String userId;

    private String userName;

    private boolean active = true;
}
