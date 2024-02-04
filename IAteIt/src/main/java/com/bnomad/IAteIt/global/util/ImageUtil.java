package com.bnomad.IAteIt.global.util;

import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.vo.Image;
import com.bnomad.IAteIt.global.vo.ImageType;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;

public class ImageUtil {

    public static Image multipartToImage(MultipartFile multipartFile, String dir) {
        String originalFilename = multipartFile.getOriginalFilename();
        String[] split = originalFilename.split("\\.");

        String onlyFileName = split[split.length - 2];
        String imageType = split[split.length - 1].toUpperCase();
        ImageType type = ImageType.valueOf(imageType);

        if (type == null) {
            throw new BusinessException(FILE_FORMAT_ERROR);
        }

        return Image.builder()
                .imageType(type)
                .uuid(UUID.randomUUID().toString())
                .dir(dir)
                .originalName(onlyFileName)
                .build();
    }
}
