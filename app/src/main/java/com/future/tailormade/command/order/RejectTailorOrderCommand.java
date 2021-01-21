package com.future.tailormade.command.order;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.order.RejectTailorOrderRequest;

public interface RejectTailorOrderCommand extends Command<RejectTailorOrderRequest, Void> {
}
