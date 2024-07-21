package com.shopdoors.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("minio")
public class S3Properties {

    private String url;

    private String accessKey;

    private String secretKey;

    private int port;

    private boolean secure;

    private long partSize;

    private String imgBucket;

    private String defaultProfileImgPath;

    private String imgProductPath;

    private String imgProfilePath;
}