package com.future.tailormade.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.future.tailormade.payload.request.image.AddImageRequest;
import com.future.tailormade.service.AmazonClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AmazonClientServiceImpl implements AmazonClientService {

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

    public Mono<String> uploadFile(AddImageRequest request) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(request.getMultipartFile());
            fileUrl = endpointUrl
                    + "/" + bucketName
                    + request.getFilePath()
                    + "/" + request.getFileName();
            uploadFileToBucket(request.getFilePath(), request.getFileName(), file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(fileUrl);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private void uploadFileToBucket(String filePath, String fileName, File file) {
        s3Client.putObject(new PutObjectRequest(
                bucketName + "/" + filePath, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}