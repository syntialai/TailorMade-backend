package com.future.tailormade.payload.response.user;

import com.future.tailormade.model.entity.base.Location;
import com.future.tailormade.model.entity.user.Education;
import com.future.tailormade.model.entity.user.Occupation;
import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.RoleEnum;
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

    private Long birthDate;

    private String image;

    private String phoneNumber;

    private RoleEnum role;

    private GenderEnum gender;

    private Location location;

    private Occupation occupation;

    private Education education;
}
