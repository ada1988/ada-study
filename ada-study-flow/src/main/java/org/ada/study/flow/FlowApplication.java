package org.ada.study.flow;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**  
 * Filename: ChannelAccountApplication.java  <br>
 *
 * Description: 项目启动项  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月5日 <br>
 *
 *  
 */
@SpringBootApplication
@MapperScan("com.miduo.channel.account.business.dao")
@EnableTransactionManagement
@EnableCaching
public class FlowApplication {
	private static final Logger		logger	= LoggerFactory.getLogger( FlowApplication.class );
    public static void main( String[] args )
    {
		ConfigurableApplicationContext contenxt = SpringApplication.run( FlowApplication.class, args);
		String[] profiles = contenxt.getEnvironment().getActiveProfiles();
		logger.error("==============启动成功，开启配置ST==============");
		for(String profile:profiles){
			logger.error("   "+profile);
		}
		logger.error("==============启动成功，开启配置ED==============");
    }
}
