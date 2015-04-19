package com.louishoughton.irrigator.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.louishoughton.irrigator")
@Import({ApisContext.class})
public class ServiceContext {


    
}
