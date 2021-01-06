package com.future.tailormade.command.auth;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.auth.RefreshTokenRequest;
import com.future.tailormade.payload.response.auth.TokenResponse;

public interface RefreshTokenCommand extends
        Command<RefreshTokenRequest, TokenResponse> {
}
