package com.future.tailormade.payload.request.auth;

import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.validation.EmailNotAvailable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @EmailNotAvailable
    private String email;

    @NotBlank
    private String password;

    @Positive
    private Long birthDate;

    private GenderEnum gender;

    private RoleEnum role;
}
