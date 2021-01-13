package com.future.tailormade.command.image.impl;

import com.future.tailormade.command.image.AddImageCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.constants.BaseConstants;
import com.future.tailormade.payload.request.image.AddImageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class AddImageCommandImpl implements AddImageCommand {

    @Override
    public Mono<String> execute(AddImageRequest request) {
        return createFile(request.getFileName(), request.getFilePath())
                .map(file -> {
                    String encodedImageFile = getEncodedBase64Image(request.getFileInBase64());
                    putFile(file, encodedImageFile);
                    return file.getPath();
                });
    }

    private Mono<File> createFile(String fileName, String filePath) {
        String directoryPath = new File("").getAbsolutePath() + ApiPath.UPLOADS_FOLDER_PATH + filePath;
        String imageFileName = "/" + fileName + BaseConstants.IMAGE_EXTENSION_PNG;
        return Mono.just(new File(directoryPath + imageFileName));
    }

    private void putFile(File imageFile, String encodedImageFile) {
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            byte[] dataBytes = Base64.getMimeDecoder().decode(encodedImageFile);
            fos.write(dataBytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getEncodedBase64Image(String fileInBase64) {
        return fileInBase64.split(BaseConstants.PART_SEPARATOR)[1];
    }
}
