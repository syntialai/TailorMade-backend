package com.future.tailormade.payload.request.user;

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
public class EditUserAdditionalInfoRequest {

    private String id;

    private Occupation occupation;

    private Education education;
}
