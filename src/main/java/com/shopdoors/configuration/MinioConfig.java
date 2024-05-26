package com.shopdoors.configuration;

import com.shopdoors.configuration.property.S3Properties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {

    private final S3Properties s3Properties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .credentials(s3Properties.getAccessKey(), s3Properties.getSecretKey())
                .endpoint(s3Properties.getUrl(), s3Properties.getPort(), s3Properties.isSecure())
                .build();
    }
}