package com.louishoughton.irrigator.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceContext.class, PersistenceContext.class})
public class ApplicationContext {
}
