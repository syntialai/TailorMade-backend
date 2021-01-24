package com.future.tailormade.command.tailor;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.tailor.GetDashboardTailorsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.tailor.GetDashboardTailorsResponse;

public interface GetDashboardTailorsCommand
        extends Command<GetDashboardTailorsRequest, BasePagingResponse<GetDashboardTailorsResponse>> {
}
