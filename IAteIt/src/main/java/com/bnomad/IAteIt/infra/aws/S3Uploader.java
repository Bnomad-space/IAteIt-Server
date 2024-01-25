package com.bnomad.IAteIt.infra.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String imageUpload(MultipartFile file, String fileName) {
        amazonS3Client.putObject(bucket, fileName, convertMultipartFileToLocalFile(file));
        return amazonS3Client.getUrl(bucket, fileName).toString();

    }

    private File convertMultipartFileToLocalFile(MultipartFile file) {
        try {
            final String pathname = System.getProperty("user.dir") + "\\upload\\" + file.getOriginalFilename();
            final File convertFile = new File(pathname);
            if (convertFile.createNewFile()) {
                try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                    fos.write(file.getBytes());
                }
                return convertFile;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
