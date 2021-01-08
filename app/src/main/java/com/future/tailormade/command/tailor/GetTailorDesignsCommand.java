package com.future.tailormade.command.tailor;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.tailor.GetTailorDesignsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.tailor.GetTailorDesignsResponse;

public interface GetTailorDesignsCommand extends
        Command<GetTailorDesignsRequest, BasePagingResponse<GetTailorDesignsResponse>> {
}
