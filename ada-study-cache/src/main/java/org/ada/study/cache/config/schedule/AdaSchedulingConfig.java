package org.ada.study.cache.config.schedule;

import java.util.Random;

import org.ada.study.cache.business.entity.FundBean;
import org.ada.study.cache.business.service.IFundDbService;
import org.ada.study.cache.common.constant.RedisPrefixContant;
import org.ada.study.cache.common.scheduling.PcFundDetailCacheScheduling;
import org.ada.study.cache.common.support.RedisCacheClient;
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
public class AdaSchedulingConfig {
	
	@Autowired
	private PcFundDetailCacheScheduling pcFundDetailCacheScheduling;
	
	/**
	 * 第一次延迟5秒后执行，之后按fixedRate的规则每2小时执行一次
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	//@Scheduled(initialDelay=5000, fixedRate=1000*60*60*2)// 每2小时执行一次
	@Scheduled(initialDelay=5000, fixedRate=1000*6)// 6秒钟执行一次
	public void scheduler() {
		pcFundDetailCacheScheduling.doWork();
	}
	
	@Autowired
	private RedisCacheClient redisCacheClient;
	@Autowired
	private IFundDbService fundDbService;
	/**
	 * 两秒钟修改一次，测试缓存是否生效
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	@Scheduled(initialDelay=1, fixedRate=1000*12)// 12秒钟执行一次
	public void flush001() {
		String cacheName = RedisPrefixContant.FUND_PC_DETAIL+"001";
		FundBean fund = fundDbService.queryDetailById( "001" );
		fund.setName( "基金 001 名称" + new Random().nextInt(200));
		try {
			redisCacheClient.setValue( cacheName, fund, 60 );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Bean
	public PcFundDetailCacheScheduling pcFundDetailCacheScheduling(){
		return new PcFundDetailCacheScheduling();
	}
	
}
