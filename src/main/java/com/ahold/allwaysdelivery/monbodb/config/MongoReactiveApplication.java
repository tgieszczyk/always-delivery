package com.ahold.allwaysdelivery.monbodb.config;

import com.ahold.allwaysdelivery.monbodb.config.converters.InegerToLocalTimeConverter;
import com.ahold.allwaysdelivery.monbodb.config.converters.LocalDateToLongConverter;
import com.ahold.allwaysdelivery.monbodb.config.converters.LocalTimeToInegerConverter;
import com.ahold.allwaysdelivery.monbodb.config.converters.LongToLocalDateConverter;
import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;
import java.util.List;


public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "ALLDELIV";
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        return com.mongodb.reactivestreams.client.MongoClients.create((String) "mongodb://ah-always-delivery-mongo:27017/ALLDELIV");
    }

    @Bean
    public CustomConversions customConversions() {
        List<Converter> converters = Arrays.asList(new LocalTimeToInegerConverter(), new InegerToLocalTimeConverter(),
                new LongToLocalDateConverter(), new LocalDateToLongConverter());
        return new MongoCustomConversions(converters);
    }
}
