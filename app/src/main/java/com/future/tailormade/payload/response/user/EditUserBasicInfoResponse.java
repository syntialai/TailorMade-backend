package com.future.tailormade.payload.response.user;

import com.future.tailormade.model.entity.base.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserBasicInfoResponse {

    private String name;

    private String phoneNumber;

    private String birthDate;

    private Location location;
}
