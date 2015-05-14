package com.louishoughton.irrigator.context;

import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;
import com.louishoughton.irrigator.web.IrrigationRequestDispatcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import static com.louishoughton.irrigator.web.IrrigationRequestDispatcherImpl.NODE_BASE_URL_PROPERTY;
import static com.louishoughton.irrigator.web.IrrigationRequestDispatcherImpl.NODE_IRRIGATION_END_POINT_PROPERTY;

@Configuration
@ComponentScan("com.louishoughton.irrigator")
@PropertySource({"classpath:/options.properties" })
@EnableScheduling
@Import({ApisContext.class})
public class ServiceContext {

    @Autowired
    Environment env;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Autowired
    public IrrigationRequestDispatcher requestDispatcher(RestTemplate restTemplate) {
        return new IrrigationRequestDispatcherImpl(restTemplate,
                env.getProperty(NODE_BASE_URL_PROPERTY),
                env.getProperty(NODE_IRRIGATION_END_POINT_PROPERTY));
    }


    
}
