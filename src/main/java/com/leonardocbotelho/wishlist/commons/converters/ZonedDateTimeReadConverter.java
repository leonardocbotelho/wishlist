package com.leonardocbotelho.wishlist.commons.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {

    @Override
    public ZonedDateTime convert(final Date date) {
        return date.toInstant().atZone(ZoneOffset.UTC);
    }

}
