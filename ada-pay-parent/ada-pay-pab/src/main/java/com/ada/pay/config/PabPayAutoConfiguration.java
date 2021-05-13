package com.ada.pay.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PabPayAutoConfiguration extends PayAutoConfiguration{
	
	@Bean("jkHeaders")
    public Map<String,String> requestHeaders() {
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml; charset=GBK");
		headers.put("Accept", "text/xml; charset=GBK");
        return headers;
    }
}
