package com.shopdoors.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("spring.mail")
public class MailProperties {

    private String host;

    private int port;

    private String username;

    private String password;

    private String properties;

}