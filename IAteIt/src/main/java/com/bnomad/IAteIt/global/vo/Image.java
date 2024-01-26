package com.bnomad.IAteIt.global.vo;

import lombok.Builder;

@Builder
public class Image {
    private String dir;
    private String uuid;
    private String originalName;
    private ImageType imageType;

    public String filePath() {
        return this.dir + "/"
                + this.uuid + "_"
                + this.originalName + "."
                + this.imageType.name();
    }
}
