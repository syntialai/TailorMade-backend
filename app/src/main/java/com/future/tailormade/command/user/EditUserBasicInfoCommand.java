package com.future.tailormade.command.user;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.user.EditUserBasicInfoRequest;
import com.future.tailormade.payload.response.user.EditUserBasicInfoResponse;

public interface EditUserBasicInfoCommand
        extends Command<EditUserBasicInfoRequest, EditUserBasicInfoResponse> {
}
