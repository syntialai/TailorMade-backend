package com.future.tailormade.payload.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserOrdersRequest {

    private String userId;

    private int page;

    private int itemPerPage;
}
