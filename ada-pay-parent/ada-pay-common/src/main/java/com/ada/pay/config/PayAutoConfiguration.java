package com.ada.pay.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@ComponentScan(basePackages = "com.ada.pay")
@Configuration
public class PayAutoConfiguration {
	
	@Bean("adaHeaders")
    public Map<String,String> requestHeaders() {
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
        return headers;
    }
	
}
