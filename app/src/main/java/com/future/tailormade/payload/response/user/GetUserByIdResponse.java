package com.future.tailormade.payload.response.user;

import com.future.tailormade.model.entity.base.Location;
import com.future.tailormade.model.entity.user.Education;
import com.future.tailormade.model.entity.user.Occupation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserByIdResponse {

    private String id;

    private String name;

    private String birthDate;

    private String image;

    private String phoneNumber;

    private Location location;

    private Occupation occupation;

    private Education education;
}
