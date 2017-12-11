package org.ada.study.elasctic.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages={"org.ada.study.elasctic.search"})
public class App 
{
	private static final Logger		logger	= LoggerFactory.getLogger( App.class );
	public static void main(String[] args) {
		ConfigurableApplicationContext contenxt = SpringApplication.run(App.class, args);
		String[] profiles = contenxt.getEnvironment().getActiveProfiles();
		logger.error("==============启动成功，开启配置ST==============");
		for(String profile:profiles){
			logger.error("   "+profile);
		}
		logger.error("==============启动成功，开启配置ED==============");
	}
}
