package com.future.tailormade.payload.response.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class BaseResponseSingleList<T> extends BaseResponse {

    private List<T> data;
}
