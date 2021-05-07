package com.ada.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ada.pay.config.EnableAdaPay;

@SpringBootApplication
@EnableAdaPay
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
    }
}
