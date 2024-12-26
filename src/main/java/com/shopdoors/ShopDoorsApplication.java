package com.shopdoors;

import com.shopdoors.configuration.property.MailProperties;
import com.shopdoors.configuration.property.S3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({S3Properties.class, MailProperties.class})
public class ShopDoorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopDoorsApplication.class, args);
    }

}
