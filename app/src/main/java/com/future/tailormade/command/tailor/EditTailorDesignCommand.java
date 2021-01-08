package com.future.tailormade.command.tailor;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.tailor.EditTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;

public interface EditTailorDesignCommand
        extends Command<EditTailorDesignRequest, AddOrEditTailorDesignResponse> {
}
