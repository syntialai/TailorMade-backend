package com.future.tailormade.payload.response.tailor;

import com.future.tailormade.model.entity.base.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTailorsResponse {

    private String id;

    private String name;

    private String image;

    private Location location;
}
