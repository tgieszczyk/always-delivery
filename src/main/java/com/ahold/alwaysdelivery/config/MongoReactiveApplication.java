package com.ahold.alwaysdelivery.config;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;


public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "ALLDELIV";
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        return com.mongodb.reactivestreams.client.MongoClients.create((String) "mongodb://mongo-delivery:27017/ALLDELIV");
    }
}
