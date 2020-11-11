package com.future.tailormade.command.auth;

import com.blibli.oss.command.Command;
import com.blibli.oss.common.response.Response;
import com.future.tailormade.payload.request.auth.SignUpRequest;

public interface SignUpCommand extends Command<SignUpRequest, Response> {
}
