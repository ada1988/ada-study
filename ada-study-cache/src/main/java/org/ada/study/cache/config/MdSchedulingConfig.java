package org.ada.study.cache.config;

import org.ada.study.cache.common.scheduling.PcFundDetailCacheScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Filename: MdSchedulingConfig.java <br>
 *
 * Description: 定时任务 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年6月19日 <br>
 *
 * 
 */
@Configuration
@EnableScheduling
public class MdSchedulingConfig {
	
	@Autowired
	private PcFundDetailCacheScheduling pcFundDetailCacheScheduling;
	
	/**
	 * 第一次延迟5秒后执行，之后按fixedRate的规则每2小时执行一次
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	@Scheduled(initialDelay=5000, fixedRate=1000*60*60*2)// 每2小时执行一次
	public void scheduler() {
		pcFundDetailCacheScheduling.doWork();
	}
	
	
	@Bean
	public PcFundDetailCacheScheduling pcFundDetailCacheScheduling(){
		return new PcFundDetailCacheScheduling();
	}
	
}
