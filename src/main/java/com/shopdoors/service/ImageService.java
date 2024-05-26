package com.shopdoors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final MinioService minioService;

    public void saveImg(String objectName, InputStream inputStream) {
        minioService.makeBucketIfNotExists(minioService.getImgBucket());
        minioService.putObject(minioService.getImgBucket(), objectName, inputStream);
    }

    public String getImgUrl(String objectName) {
        Objects.requireNonNull(objectName);
        return minioService.getObjectUrl(minioService.getImgBucket(), objectName);
    }
}
