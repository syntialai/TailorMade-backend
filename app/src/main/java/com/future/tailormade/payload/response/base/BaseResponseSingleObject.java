package com.future.tailormade.payload.response.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class BaseResponseSingleObject<T> extends BaseResponse {

    private T data;
}
