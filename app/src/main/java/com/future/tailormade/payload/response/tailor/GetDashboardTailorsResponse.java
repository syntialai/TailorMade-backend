package com.future.tailormade.payload.response.tailor;

import com.future.tailormade.model.entity.base.Location;
import com.future.tailormade.model.entity.user.TailorDesign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDashboardTailorsResponse {

    private String id;

    private String name;

    private String image;

    private Location location;

    private List<TailorDesign> designs;
}
