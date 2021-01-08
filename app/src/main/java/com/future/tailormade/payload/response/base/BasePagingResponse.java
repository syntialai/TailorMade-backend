package com.future.tailormade.payload.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasePagingResponse<T> {

    private List<T> data;

    private Integer totalItem;
}
