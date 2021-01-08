package com.future.tailormade.payload.request.tailor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTailorDesignRequest {

    private String id;

    private String tailorId;
}
