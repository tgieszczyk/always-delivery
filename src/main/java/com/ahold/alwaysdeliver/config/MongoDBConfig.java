package com.ahold.alwaysdeliver.config;

import org.springframework.beans.factory.annotation.Value;

public class MongoDBConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.connectionTimeout}")
    private int connectionTimeout;

//    @Bean
//    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
//        MongoClientURI mongoClientUri = new MongoClientURI(uri,
//                MongoClientOptions.builder().maxConnectionIdleTime(connectionTimeout));
//
//        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClientUri);
//
//        return simpleMongoDbFactory;
//    }
//
//    @SuppressWarnings("rawtypes")
//    @Bean
//    public MongoTemplate mongoTemplate(@Autowired MongoDbFactory mongoDbFactory) throws UnknownHostException {
//        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory),
//                new MongoMappingContext());
//        List<Converter> converters = Arrays.asList(new LocalTimeToInegerConverter(), new InegerToLocalTimeConverter(),
//                new TimeZoneInformationToStringConverter(), new StringToTimeZoneInformationConverter(),
//                new AuthenticationReadConverter(), new StringToCampaignCardTypeConverter(),
//                new CampaignCardTypeToStringConverter(), new LongToLocalDateConverter(), new LocalDateToLongConverter(),
//                new DBObjectToChatUserLoanApplicationConverter(), new ChatUserLoanApplicationToDBObjectConverter());
//        converter.setCustomConversions(new CustomConversions(converters));
//        converter.afterPropertiesSet();
//        return new MongoTemplate(mongoDbFactory, converter);
//    }

}
