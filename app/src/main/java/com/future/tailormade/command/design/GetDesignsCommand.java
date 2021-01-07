package com.future.tailormade.command.design;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.design.GetDesignsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.design.GetDesignsResponse;

public interface GetDesignsCommand extends
        Command<GetDesignsRequest, BasePagingResponse<GetDesignsResponse>> {
}
