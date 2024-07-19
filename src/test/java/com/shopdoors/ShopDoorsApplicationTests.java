package com.shopdoors;

import com.shopdoors.config.ContainersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.AbstractEnvironment;

class ShopDoorsApplicationTests {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test");

        System.setProperty("postgres.container.port", "8971");

        SpringApplication.from(ShopDoorsApplication::main)
                .with(ContainersConfiguration.class)
                .run(args);
    }
}