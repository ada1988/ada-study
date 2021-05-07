package com.ada.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@ComponentScan(basePackages = "com.ada.pay")
@Configuration
public class PayAutoConfiguration {
	
	@Bean
    public String jkTest() {
        return "自定义配置类";
    }
	
}
