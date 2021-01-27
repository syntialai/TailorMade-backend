package com.future.tailormade.payload.request.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddImageRequest {

    @NotBlank
    private MultipartFile multipartFile;

    private String fileName;

    private String filePath;
}
