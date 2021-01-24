package com.future.tailormade.payload.response.auth;

import com.future.tailormade.model.entity.auth.Token;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    private Token token;

    private GetUserByIdResponse user;
}
