package com.shopdoors.service;

import com.shopdoors.configuration.property.S3Properties;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final S3Properties s3Properties;

    public void makeBucketIfNotExists(String bucketName) {
        try {
            boolean flag = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build());

            if (!flag) {
                log.info("Making bucket {}", bucketName);
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("Bucket {} created", bucketName);
            }
        } catch (InsufficientDataException
                 | ErrorResponseException
                 | InternalException
                 | InvalidKeyException
                 | InvalidResponseException
                 | IOException
                 | NoSuchAlgorithmException
                 | ServerException
                 | XmlParserException e) {
            log.error("Error while making bucket: {}", bucketName, e);
        }
    }

    public void putObject(String bucketName, String objectName, InputStream inputStream) {
        putObject(bucketName, objectName, MediaType.APPLICATION_OCTET_STREAM_VALUE, inputStream);
    }

    public void putObject(String bucketName, String objectName, String contentType, InputStream inputStream) {
        putObject(bucketName, objectName, contentType, inputStream, -1);
    }


    public void putObject(
            String bucketName, String objectName, String contentType, InputStream inputStream, int length) {
        try {
            log.info("Putting object {} into bucket {}", objectName, bucketName);
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).contentType(contentType).stream(
                                    inputStream, length, s3Properties.getPartSize())
                            .build());
            log.info("Successfully put object {} into bucket {}", objectName, bucketName);
        } catch (InsufficientDataException
                 | ErrorResponseException
                 | InternalException
                 | InvalidKeyException
                 | InvalidResponseException
                 | IOException
                 | NoSuchAlgorithmException
                 | ServerException
                 | XmlParserException e) {
            log.error("Error while getting object url: {}", objectName, e);
        }
    }


    public String getObjectUrl(String bucketName, String objectName) {
        try {
            log.info("Getting object url for bucket {} and object {}", bucketName, objectName);
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(60 * 60)
                            .build());
        } catch (InsufficientDataException
                 | ErrorResponseException
                 | InternalException
                 | InvalidKeyException
                 | InvalidResponseException
                 | IOException
                 | NoSuchAlgorithmException
                 | ServerException
                 | XmlParserException e) {
            log.error("Error while getting object url: {}", objectName, e);
            return null;
        }
    }

    public String getImgBucket() {
        return s3Properties.getImgBucket();
    }

    public String getImgProfilePath() {
        return s3Properties.getImgProfilePath();
    }

    public String getImgProductPath() {
        return s3Properties.getImgProductPath();
    }
}
