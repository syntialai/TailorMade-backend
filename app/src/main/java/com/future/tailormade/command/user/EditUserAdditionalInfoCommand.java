package com.future.tailormade.command.user;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.user.EditUserAdditionalInfoRequest;
import com.future.tailormade.payload.response.user.EditUserAdditionalInfoResponse;

public interface EditUserAdditionalInfoCommand
        extends Command<EditUserAdditionalInfoRequest, EditUserAdditionalInfoResponse> {
}
