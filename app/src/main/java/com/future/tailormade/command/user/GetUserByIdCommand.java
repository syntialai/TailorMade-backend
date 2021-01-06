package com.future.tailormade.command.user;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;

public interface GetUserByIdCommand extends Command<String, GetUserByIdResponse> {
}
