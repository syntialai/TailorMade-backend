package com.future.tailormade.payload.response.base;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(builderMethodName = "baseBuilder")
public class BaseResponse {

    private Integer code;

    private String status;
}
