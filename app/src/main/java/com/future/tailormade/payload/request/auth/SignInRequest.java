package com.future.tailormade.payload.request.auth;

import com.future.tailormade.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @NotBlank
    @Email(message = "InvalidFormat")
    private String username;

    @NotBlank
    private String password;
    
    private RoleEnum role;
}
