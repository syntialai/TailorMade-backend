package com.future.tailormade.payload.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseSingleListPaging<T> extends BaseResponse {

    private List<T> data;

    private BaseResponsePaging paging;
}
