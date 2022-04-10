package com.natwest.hiring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        //        restTemplate.getMessageConverters().stream()
//                .filter(msgConverters -> msgConverters instanceof MappingJackson2HttpMessageConverter)
//                .findFirst()
//                .ifPresent(jacksonConverter -> ((MappingJackson2HttpMessageConverter) jacksonConverter)
//                        .getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL));
        return new RestTemplate();
    }
}
