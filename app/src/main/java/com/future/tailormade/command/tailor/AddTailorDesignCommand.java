package com.future.tailormade.command.tailor;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.tailor.AddTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;

public interface AddTailorDesignCommand
        extends Command<AddTailorDesignRequest, AddOrEditTailorDesignResponse> {
}
