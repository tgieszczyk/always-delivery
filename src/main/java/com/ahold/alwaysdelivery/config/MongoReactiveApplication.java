package com.ahold.alwaysdelivery.config;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "MyDb";
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        return com.mongodb.reactivestreams.client.MongoClients.create((String)"mongodb://localhost:27017/ALLDELIV");
    }
}
