package com.louishoughton.irrigator.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.louishoughton.irrigator.valve")
public class NodeContext {
}
