package org.database.change.start;

import org.database.change.config.AmqpConfig;
import org.database.change.db.monitor.start.DbMonitorStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

/**
 * Filename: Application.java <br>
 *
 * Description: 程序启动类 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年4月21日 <br>
 *
 * 
 */
@ComponentScan(basePackages = "org.database.change")
@EnableAutoConfiguration(exclude = { AmqpConfig.class })
public class Application {
	public static void main(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args );
		context.registerShutdownHook();
		context.start();
		
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep( 3L );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Bean(initMethod = "start")
	@Scope(scopeName = "singleton")
	public DbMonitorStart dbMonitorStart() {
		return new DbMonitorStart();
	}
}
