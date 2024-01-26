package com.bnomad.IAteIt.global.util;

import com.bnomad.IAteIt.global.vo.Image;
import com.bnomad.IAteIt.global.vo.ImageType;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class ImageUtil {

    public static Image multipartToImage(MultipartFile multipartFile, String dir) {
        String originalFilename = multipartFile.getOriginalFilename();
        String[] split = originalFilename.split("\\.");

        String onlyFileName = split[split.length - 2];
        String imageType = split[split.length - 1].toUpperCase();
        ImageType type = ImageType.valueOf(imageType);

        if (type == null) {
            throw new RuntimeException("파일 형식이 맞지 않습니다");
        }

        return Image.builder()
                .imageType(type)
                .uuid(UUID.randomUUID().toString())
                .dir(dir)
                .originalName(onlyFileName)
                .build();
    }
}
