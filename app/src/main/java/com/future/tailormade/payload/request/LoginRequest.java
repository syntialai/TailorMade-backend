package com.future.tailormade.payload.request;

import com.future.tailormade.config.BaseConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    @Length(
            min = BaseConstants.PHONE_NUMBER_MIN_LENGTH,
            max = BaseConstants.PHONE_NUMBER_MAX_LENGTH
    )
    private String phoneNumber;

    @NotBlank
    @NumberFormat
    private String verificationCode;
}
