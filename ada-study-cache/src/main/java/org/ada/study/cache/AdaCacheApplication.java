package org.ada.study.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages={"org.ada.study.cache"})
@ServletComponentScan("org.ada.study.cache.common.filter")
public class AdaCacheApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run( AdaCacheApplication.class, args );
    }
}
