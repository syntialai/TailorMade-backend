package com.future.tailormade.command.tailor;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.tailor.GetTailorsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.tailor.GetTailorsResponse;

public interface GetTailorsCommand
        extends Command<GetTailorsRequest, BasePagingResponse<GetTailorsResponse>> {
}
