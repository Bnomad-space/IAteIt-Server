package com.bnomad.IAteIt.infra.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.util.ImageUtil;
import com.bnomad.IAteIt.global.vo.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String imageUpload(MultipartFile multipartFile, String dir) {
        Image image = ImageUtil.multipartToImage(multipartFile, dir);
        File uploadFile = convertMultipartFileToLocalFile(multipartFile);
        amazonS3Client.putObject(
                new PutObjectRequest(
                        bucket,
                        image.filePath(),
                        uploadFile
                )
                .withCannedAcl(CannedAccessControlList.PublicRead));
        String url = amazonS3Client.getUrl(bucket, image.filePath()).toString();

        deleteLocalFile(uploadFile);
        return url;
    }

    public void deleteImage(String url) {
        String dir = url.split("/")[3];
        String fileName = url.split("/")[4];

        String filepath = dir + "/" + fileName;
        amazonS3Client.deleteObject(bucket, filepath);
    }

    private void deleteLocalFile(File file) {
        if (file.delete()) {
            return;
        }
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
            throw new BusinessException(FILE_CONVERT_ERROR);
        } catch (IOException e) {
            throw new BusinessException(FILE_CONVERT_ERROR);
        }
    }

}
