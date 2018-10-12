package com.ahold.allwaysdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = {"com.ahold.allwaysdelivery"})
@EnableWebFlux
@EnableReactiveMongoRepositories
@EnableMongoRepositories
@Configuration
public class AlwaysDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlwaysDeliveryApplication.class, args);
    }
}
