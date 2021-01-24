package com.future.tailormade.command.auth;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.auth.SignUpRequest;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;

public interface SignUpCommand extends Command<SignUpRequest, GetUserByIdResponse> {
}
