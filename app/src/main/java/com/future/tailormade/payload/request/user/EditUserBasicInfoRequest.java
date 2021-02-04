package com.future.tailormade.payload.request.user;

import com.future.tailormade.model.entity.base.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserBasicInfoRequest {

    private String id;

    @NotBlank
    private String name;

    private String phoneNumber;

    @Positive
    private Long birthDate;

    private Location location;
}
