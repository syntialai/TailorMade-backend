package com.future.tailormade.command.order;

import com.blibli.oss.command.Command;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.payload.request.order.GetUserOrderByIdRequest;

public interface GetUserOrderByIdCommand extends Command<GetUserOrderByIdRequest, Order> {
}
