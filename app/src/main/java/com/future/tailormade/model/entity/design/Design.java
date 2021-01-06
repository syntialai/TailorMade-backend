package com.future.tailormade.model.entity.design;

import com.future.tailormade.constants.DesignConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = DesignConstants.DESIGN_COLLECTION)
public class Design {

    private String id;

    private String title;

    private String image;

    private Double price;

    private Double discount = 0.0;

    private List<Size> size;

    private List<Color> color;

    private String description;

    private List<String> category;

    private String tailorId;

    private String tailorName;

    private String userId;

    private String userName;

    private boolean active = true;
}
