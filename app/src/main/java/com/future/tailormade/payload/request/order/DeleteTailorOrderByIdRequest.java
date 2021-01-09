package com.future.tailormade.payload.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTailorOrderByIdRequest {

    private String id;

    private String tailorId;
}
