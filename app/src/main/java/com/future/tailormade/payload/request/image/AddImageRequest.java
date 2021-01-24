package com.future.tailormade.payload.request.image;

import com.future.tailormade.validation.ImageInvalidType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddImageRequest {

    @NotBlank
    @ImageInvalidType
    private String fileInBase64;

    private String fileName;

    private String filePath;
}
