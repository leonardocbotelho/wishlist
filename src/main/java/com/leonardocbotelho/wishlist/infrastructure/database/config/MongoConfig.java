package com.leonardocbotelho.wishlist.infrastructure.database.config;

import com.leonardocbotelho.wishlist.commons.converters.ZonedDateTimeReadConverter;
import com.leonardocbotelho.wishlist.commons.converters.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(List.of(
                new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()
        ));
    }

}