package com.future.tailormade.payload.request.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetImageRequest {

    private String fileName;

    private String filePath;
}
