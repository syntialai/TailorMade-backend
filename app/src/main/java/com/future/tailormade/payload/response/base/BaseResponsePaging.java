package com.future.tailormade.payload.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponsePaging {

    private Integer page;

    private Integer itemPerPage;

    private Integer totalPage;
}
