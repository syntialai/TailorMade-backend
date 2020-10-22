package com.future.tailormade.payload.request;

import com.future.tailormade.config.BaseConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Length(
            min = BaseConstants.PHONE_NUMBER_MIN_LENGTH,
            max = BaseConstants.PHONE_NUMBER_MAX_LENGTH
    )
    private String phoneNumber;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String birthDate;

    @NotBlank
    private String gender;

    @NotBlank
    private String role;
}
