package org.ada.study.cache.common.cache.v2.support.redis;

import java.io.IOException;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**  
 * Filename: SingletonPoolConfig.java  <br>
 *
 * Description:  jedisPool redis客户端连接池<br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年11月30日 <br>
 *
 *  
 */

public class SingletonJedisPool {
	private static final Logger log=LoggerFactory.getLogger(SingletonJedisPool.class);

	private int port=6379;

	private String host="127.0.0.1";

	private int maxTotal = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;

	private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

	private int minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;

	private static JedisPool jedisPool = null;

	private static SingletonJedisPool instance = new SingletonJedisPool();  

	private SingletonJedisPool(){
		
		GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		jedisPool=new JedisPool(poolConfig, host,port);

		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>[jedisPool]初始化成功!");
	}  

	public static SingletonJedisPool getInstance() {  
		return instance;  
	}  

	public static JedisPool getJedisPool() {  
		return jedisPool;  
	} 
	public static void returnResource(Jedis jedis){
		jedisPool.returnResource(jedis);
	}
}
