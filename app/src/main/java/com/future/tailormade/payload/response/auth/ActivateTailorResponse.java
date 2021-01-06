package com.future.tailormade.payload.response.auth;

import com.future.tailormade.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivateTailorResponse {

    private RoleEnum role;
}
