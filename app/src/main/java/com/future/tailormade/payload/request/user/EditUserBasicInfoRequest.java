package com.future.tailormade.payload.request.user;

import com.future.tailormade.model.entity.base.Location;
import com.future.tailormade.validation.PhoneNumberNotAvailable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserBasicInfoRequest {

    @NotBlank
    private String name;

    @PhoneNumberNotAvailable
    private String phoneNumber;

    @NotBlank
    @DateTimeFormat
    private String birthDate;

    private Location location;
}
