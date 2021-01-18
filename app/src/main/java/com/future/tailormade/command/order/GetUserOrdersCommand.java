package com.future.tailormade.command.order;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.order.GetUserOrdersRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.order.GetOrdersResponse;

public interface GetUserOrdersCommand
        extends Command<GetUserOrdersRequest, BasePagingResponse<GetOrdersResponse>> {
}
