package com.future.tailormade.controller;

import com.blibli.oss.command.CommandExecutor;
import com.future.tailormade.command.image.GetImageCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.image.GetImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class ImageController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(ApiPath.IMAGE_FILE_PATH_FILE_NAME)
    public Mono<ResponseEntity<byte[]>> getImage(
            @PathVariable("filePath") String filePath,
            @PathVariable("fileName") String fileName
    ) {
        GetImageRequest request = GetImageRequest.builder()
                .filePath(filePath)
                .fileName(fileName)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return commandExecutor.execute(GetImageCommand.class, request)
                .map(file -> new ResponseEntity<>(file, headers, HttpStatus.OK))
                .subscribeOn(Schedulers.elastic());
    }
}
