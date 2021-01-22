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
        File file = createFile(request.getFileName(), request.getFilePath());
        String encodedImageFile = getEncodedBase64Image(request.getFileInBase64());
        putFile(file, encodedImageFile);
        return Mono.just(file.getPath());
    }

    private File createFile(String fileName, String filePath) {
        File directoryFile = new File("");
        String directoryPath = directoryFile.getAbsolutePath() + ApiPath.UPLOADS_FOLDER_PATH + filePath;
        String imageFileName = "/" + fileName + BaseConstants.IMAGE_EXTENSION_JPEG;
        return new File(directoryPath + imageFileName);
    }

    private void putFile(File imageFile, String encodedImageFile) {
        byte[] dataBytes = Base64.getMimeDecoder().decode(encodedImageFile);
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(dataBytes);
            fos.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getEncodedBase64Image(String fileInBase64) {
        return fileInBase64.split(BaseConstants.PART_SEPARATOR)[1];
    }
}
