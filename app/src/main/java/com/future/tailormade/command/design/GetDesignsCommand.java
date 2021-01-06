package com.future.tailormade.command.design;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.design.GetDesignsRequest;
import com.future.tailormade.payload.response.design.GetDesignsResponse;

import java.util.List;

public interface GetDesignsCommand extends Command<GetDesignsRequest, List<GetDesignsResponse>> {
}
