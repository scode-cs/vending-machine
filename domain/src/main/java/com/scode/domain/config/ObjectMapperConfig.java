package com.scode.domain.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSZ");

        // Object Mapper object with configuration
        ObjectMapper mapper = new ObjectMapper();
//          mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Properties configuration
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        // Date format with timezone
        mapper.setDateFormat(dateFormat);
        mapper.setTimeZone(TimeZone.getTimeZone("GMT"));

        /*{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ");

            // Object Mapper object with configuration
            ObjectMapper mapper = new ObjectMapper();
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            // Properties configuration
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

            // Date format with timezone
            mapper.setDateFormat(dateFormat);
//        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
//        mapper.setTimeZone(TimeZone.getDefault());
            mapper.setTimeZone(TimeZone.getTimeZone("GMT"));

            return mapper;
        }*/

        return mapper;
    }
}
