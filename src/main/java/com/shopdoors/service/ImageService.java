package com.shopdoors.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final MinioService minioService;

    public void saveImg(String objectName, InputStream inputStream) {
        log.info("Saving image to {}", objectName);
        minioService.makeBucketIfNotExists(minioService.getImgBucket());
        minioService.putObject(minioService.getImgBucket(), objectName, inputStream);
        log.info("Image saved to {}", objectName);
    }

    public String getImgUrl(String objectName) {
        log.info("Retrieving image from {}", objectName);
        Objects.requireNonNull(objectName);
        return minioService.getObjectUrl(minioService.getImgBucket(), objectName);
    }
}
