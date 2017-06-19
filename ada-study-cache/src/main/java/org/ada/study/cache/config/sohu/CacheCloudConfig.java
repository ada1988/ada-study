package org.ada.study.cache.config.sohu;



import org.ada.study.cache.common.support.MiduoClusterServersConfig;
import org.ada.study.cache.common.support.MiduoRedissonConfig;
import org.ada.study.cache.common.support.RedisCacheClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisCluster;

import com.sohu.tv.builder.ClientBuilder;
/**  
 * Filename: CachecloudConfig.java  <br>
 *
 * Description: 搜狐redis集群 配置  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月13日 <br>
 *
 *  
 */
@Configuration
@EnableConfigurationProperties(CacheCloudProperties.class)
public class CacheCloudConfig {
	
	@Autowired
	private CacheCloudProperties properties;
	
	/**
	 * 基金缓存使用的cache，并封装锁分布式锁（redisson）
	 */
	@Bean("jedisCluster")
	public JedisCluster jedisCluster(){
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		return ClientBuilder.redisCluster(properties.getAppid()).setJedisPoolConfig(poolConfig) 
			    .setConnectionTimeout(1000) 
			    .setSoTimeout(1000) 
			    .setMaxRedirections(5).build(); 
	}
	
	@Bean("miduoClusterServersConfig")
	public MiduoClusterServersConfig miduoClusterServersConfig(){
		return new MiduoClusterServersConfig( properties.getAppid().toString(), properties.getScanInterval() );
	}
	
	@Bean("miduoRedissonConfig")
	public MiduoRedissonConfig miduoRedissonConfig(){
		return new MiduoRedissonConfig( miduoClusterServersConfig() );
	}
	
	@Bean("redisson")
	public RedissonClient redissonClient(){
		return Redisson.create(miduoRedissonConfig());
	}
	
	@Bean("redisCacheClient")
	public RedisCacheClient redisCacheClient(){
		return new RedisCacheClient( jedisCluster(), redissonClient(), properties.getLockName() );
	}
}
