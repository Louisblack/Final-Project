package com.louishoughton.irrigator.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.louishoughton.irrigator")
@Import({ApisContext.class})
public class ServiceContext {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
