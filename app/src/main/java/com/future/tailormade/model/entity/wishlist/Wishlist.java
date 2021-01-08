package com.future.tailormade.model.entity.wishlist;

import com.future.tailormade.model.entity.base.BaseEntity;
import io.github.classgraph.json.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist extends BaseEntity {

    @Id
    private String id;

    private String userId;

    private String userName;

    private String tailorId;

    private String tailorName;

    private Integer quantity;

    private WishlistDesign design;
}
