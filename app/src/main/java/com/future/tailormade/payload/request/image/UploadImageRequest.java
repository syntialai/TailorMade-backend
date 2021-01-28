package com.future.tailormade.payload.request.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageRequest {

    private FilePart image;

    private String filePath;
}
