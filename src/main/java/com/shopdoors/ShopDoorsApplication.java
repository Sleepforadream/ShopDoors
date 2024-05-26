package com.shopdoors;

import com.shopdoors.configuration.property.S3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({S3Properties.class})
public class ShopDoorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopDoorsApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(AuthorizeUserRepository repository) {
//        return args -> {
//            try {
//                var entity = repository.findByNickName("nervan");
//                System.err.println("Success: " + entity.get().getFirstName());
//            } catch (Exception e) {
//                System.err.println("Error: Unable to connect to the database.");
//                e.printStackTrace();
//            }
//        };
//    }

}
