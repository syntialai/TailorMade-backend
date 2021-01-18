package com.future.tailormade.command.image.impl;

import com.future.tailormade.command.image.AddImageCommand;
import com.future.tailormade.constants.BaseConstants;
import com.future.tailormade.payload.request.image.AddImageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

@Service
public class AddImageCommandImpl implements AddImageCommand {

    private static final String STATIC_IMAGES = "/src/main/resources/static/images/";

    @Override
    public Mono<Object> execute(AddImageRequest request) {
        return createFile(request.getFileName(), request.getFilePath())
                .map(file -> {
                    String encodedImageFile = getEncodedBase64Image(request.getFileInBase64());
                    return putFile(file, encodedImageFile);
                });
    }

    private Mono<File> createFile(String fileName, String filePath) {
        String directoryPath = new File("").getAbsolutePath() + STATIC_IMAGES + filePath;
        String imageFileName = "/" + fileName + BaseConstants.IMAGE_EXTENSION_PNG;
        return Mono.just(new File(directoryPath + imageFileName));
    }

    private Mono<Void> putFile(File imageFile, String encodedImageFile) {
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            byte[] dataBytes = Base64.getMimeDecoder().decode(encodedImageFile);
            fos.write(dataBytes);
            return Mono.empty().then();
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    private String getEncodedBase64Image(String fileInBase64) {
        return fileInBase64.split(BaseConstants.PART_SEPARATOR)[1];
    }
}
