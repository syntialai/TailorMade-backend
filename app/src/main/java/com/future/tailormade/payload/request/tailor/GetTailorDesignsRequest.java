package com.future.tailormade.payload.request.tailor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTailorDesignsRequest {

    private String tailorId;

    private int page;

    private int itemPerPage;
}
