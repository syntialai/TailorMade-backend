package com.future.tailormade.payload.response.user;

import com.future.tailormade.model.entity.user.Education;
import com.future.tailormade.model.entity.user.Occupation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserAdditionalInfoResponse {

    private Occupation occupation;

    private Education education;
}
