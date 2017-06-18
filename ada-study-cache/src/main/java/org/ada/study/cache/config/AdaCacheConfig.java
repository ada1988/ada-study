package org.ada.study.cache.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ada.study.cache.common.constant.AdaCacheConstant;
import org.ada.study.cache.spring.AdaKeyGenerator;
import org.ada.study.cache.spring.MapDbSpringCache;
import org.ada.study.cache.spring.MapDbSpringCacheMannger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filename: AdaCacheConfig.java <br>
 *
 * Description: 自定义缓存配置<br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年6月18日 <br>
 *
 * 
 */
@Configuration
@EnableCaching(proxyTargetClass = true) 
public class AdaCacheConfig extends CachingConfigurerSupport{
	/**
	 * spring支持 站内缓存
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月16日
	 */
	@Bean("cacheManager")
	public CacheManager cacheManager(){
		MapDbSpringCacheMannger mapDbSpringCacheMannger = new MapDbSpringCacheMannger();
		mapDbSpringCacheMannger.setCaches( cacheCollection() );
		return mapDbSpringCacheMannger;
	}
	
	/**
	 * 缓存名称集合
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月16日
	 */
	public List<String> cacheNames(){
		List<String> cacheKeys = new ArrayList<String>();
		for(AdaCacheConstant.AdaCacheEnum cacheKey:AdaCacheConstant.AdaCacheEnum.values()){
			cacheKeys.add( cacheKey.getCacheName() );
		}
		return cacheKeys;
	}
	
	/**
	 * 生产缓存对象
	 * @return 每个缓存：默认2个小时过期,1000个对象
	 * @author: CZD  
	 * @Createtime: 2017年6月16日
	 */
	@Bean
	public Collection<MapDbSpringCache> cacheCollection(){
		List<MapDbSpringCache> caches = new ArrayList<MapDbSpringCache>();
		MapDbSpringCache cache = null;
		for(String cacheName:cacheNames()){
			System.out.println("装载缓存名称："+cacheName);
			cache = new MapDbSpringCache( cacheName );
			caches.add( cache );
		}
		return caches;
	}
	@Bean  
	public KeyGenerator keyGenerator() {
		return new AdaKeyGenerator();
	}

}
