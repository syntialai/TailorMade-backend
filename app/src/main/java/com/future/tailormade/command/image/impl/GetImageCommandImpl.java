package com.future.tailormade.command.image.impl;

import com.future.tailormade.command.image.GetImageCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.payload.request.image.GetImageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class GetImageCommandImpl implements GetImageCommand {

    @Override
    public Mono<byte[]> execute(GetImageRequest request) {
        return createImageFile(request).map(this::loadImage);
    }

    private Mono<File> createImageFile(GetImageRequest request) {
        String fileName = request.getFilePath() + "/" + request.getFileName();
        return Mono.just(new File(ApiPath.STATIC_IMAGES_PATH + fileName));
    }

    private byte[] loadImage(File file) {
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);

            return bytes;
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }
}
