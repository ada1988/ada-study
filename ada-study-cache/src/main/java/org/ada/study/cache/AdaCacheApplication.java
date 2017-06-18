package org.ada.study.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.ada.study.cache"})
public class AdaCacheApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run( AdaCacheApplication.class, args );
    }
}
