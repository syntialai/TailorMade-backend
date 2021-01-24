package com.future.tailormade.command.auth;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.auth.SignInRequest;
import com.future.tailormade.payload.response.auth.SignInResponse;

public interface SignInCommand extends Command<SignInRequest, SignInResponse> {
}
