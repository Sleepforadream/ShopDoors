package com.shopdoors.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

@TestConfiguration
public class WireMockConfig {

    @Autowired
    ObjectMapper objectMapper;

    private static final WireMockServer wireMockServer = new WireMockServer(8888);

    @PostConstruct
    public void startWireMock() {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @PreDestroy
    public void stopWireMock() {
        wireMockServer.stop();
    }

    @Bean
    public WireMockServer wireMockServer() {
        return wireMockServer;
    }

}
