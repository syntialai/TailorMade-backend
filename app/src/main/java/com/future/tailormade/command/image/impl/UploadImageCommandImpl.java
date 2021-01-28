package com.future.tailormade.command.image.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.future.tailormade.command.image.UploadImageCommand;
import com.future.tailormade.constants.BaseConstants;
import com.future.tailormade.payload.request.image.UploadImageRequest;
import com.future.tailormade.payload.response.image.UploadImageResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UploadImageCommandImpl implements UploadImageCommand {

    private AmazonS3 s3Client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3Client = new AmazonS3Client(credentials);
    }

    @SneakyThrows
    @Override
    public Mono<UploadImageResponse> execute(UploadImageRequest request) {
        String fileName = "" + System.currentTimeMillis();
        return uploadFile(request.getImage(), request.getFilePath(), fileName)
                .map(this::createResponse);
    }

    private Mono<String> uploadFile(FilePart image, String filePath, String fileName) throws IOException {
        String fileUrl = "";
        try {
            File file = convertFilePartToFile(image);
            fileUrl = endpointUrl
                    + "/" + bucketName
                    + "/" + filePath
                    + "/" + fileName + BaseConstants.IMAGE_EXT_JPEG;
            uploadFileToBucket(filePath, fileName + BaseConstants.IMAGE_EXT_JPEG, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(fileUrl);
    }

    private UploadImageResponse createResponse(String fileUrl) {
        return UploadImageResponse.builder().imageUrl(fileUrl).build();
    }

    private File convertFilePartToFile(FilePart file) throws IOException {
        File convertedFile = new File(file.filename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        getBytes(file).forEach(bytes -> {
            try {
                fos.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fos.close();
        return convertedFile;
    }

    private void uploadFileToBucket(String filePath, String fileName, File file) {
        s3Client.putObject(new PutObjectRequest(
                bucketName + "/" + filePath, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private List<byte[]> getBytes(FilePart filePart) {
        return filePart.content().flatMap(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            return Mono.just(bytes);
        }).collectList().subscribeOn(Schedulers.elastic()).block();
    }
}