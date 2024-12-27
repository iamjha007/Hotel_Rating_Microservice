package com.sl.user.service.userService.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfigs {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
