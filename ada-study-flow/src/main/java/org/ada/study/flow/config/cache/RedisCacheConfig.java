package org.ada.study.flow.config.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer; 
import com.miduo.channel.account.common.constant.MiduoCacheConstant;

/**
 * Filename: RedisCacheConfig.java <br>
 *
 * Description: RedisCacheConfig缓存配置 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年12月5日 <br>
 *
 * 
 */
@Configuration
public class RedisCacheConfig {
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory( cf );
		redisTemplate.setKeySerializer( new StringRedisSerializer() );

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer( Object.class );
		ObjectMapper om = new ObjectMapper();
		om.setVisibility( PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY );
		om.enableDefaultTyping( ObjectMapper.DefaultTyping.NON_FINAL );
		jackson2JsonRedisSerializer.setObjectMapper( om );

		redisTemplate.setValueSerializer( jackson2JsonRedisSerializer );
		redisTemplate.setHashValueSerializer( jackson2JsonRedisSerializer );
		return redisTemplate;
	}

	@Bean("redisCacheManager")
	public org.springframework.cache.CacheManager redisCacheManager(RedisTemplate<Object, Object> redisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager( redisTemplate, cacheNames(), false );
		redisCacheManager.setUsePrefix( true );// 使用前缀{cacheName:自定义结构}
												// 未使用前缀{自定义结构}
		redisCacheManager.setDefaultExpiration( MiduoCacheConstant.TIME_OUT );// 默认超时时间
		redisCacheManager.setExpires( cacheNameAndTimes() );// 自定义超时时间
		return redisCacheManager;
	}

	/**
	 * 缓存名称集合
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年6月16日
	 */
	public List<String> cacheNames() {
		List<String> cacheNames = new ArrayList<String>();
		for (MiduoCacheConstant.MiduoCacheEnum cacheEnum : MiduoCacheConstant.MiduoCacheEnum.values()) {
			cacheNames.add( cacheEnum.getCacheName() );
		}
		return cacheNames;
	}

	/**
	 * 缓存名称--超时时间 集合
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年6月16日
	 */
	public Map<String, Long> cacheNameAndTimes() {
		Map<String, Long> cacheNames = new HashMap<String, Long>();
		for (MiduoCacheConstant.MiduoCacheEnum cacheEnum : MiduoCacheConstant.MiduoCacheEnum.values()) {
			cacheNames.put( cacheEnum.getCacheName(), cacheEnum.getTimeOut() );
		}
		return cacheNames;
	}

}
