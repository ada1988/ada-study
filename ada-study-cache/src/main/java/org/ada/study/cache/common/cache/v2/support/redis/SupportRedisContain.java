package org.ada.study.cache.common.cache.v2.support.redis;

import org.ada.study.cache.common.cache.v2.IBaseContain;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.Jedis;

/**  
 * Filename: BaseCache.java  <br>
 *
 * Description: redis实现容器  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月22日 <br>
 *
 *  
 */

public class SupportRedisContain<K, V, S> implements IBaseContain<K, V, S>{
	private byte[] redisKey;
	
	public SupportRedisContain(String redisKey){
		this.redisKey = redisKey.getBytes();
	}
	
	private RedisSerializer<Object> redisSerializer=new JdkSerializationRedisSerializer();
	
	public V get(K key) {
		Jedis jedis=SingletonJedisPool.getJedisPool().getResource();
		V value=(V)redisSerializer.deserialize(jedis.hget(redisKey, redisSerializer.serialize(key.toString())));
		SingletonJedisPool.getJedisPool().returnResource(jedis);
		return value;
	}

	public void remove(K key) {
		Jedis jedis=SingletonJedisPool.getJedisPool().getResource();
		jedis.hdel(redisKey, redisSerializer.serialize(key.toString()));
		SingletonJedisPool.getJedisPool().returnResource(jedis);
	}

	public void put(K key, V value) {
		Jedis jedis=SingletonJedisPool.getJedisPool().getResource();
		jedis.hset(this.redisKey, redisSerializer.serialize(key.toString()), redisSerializer.serialize(value));
		SingletonJedisPool.getJedisPool().returnResource(jedis);
	}

	public void clear() {
		Jedis jedis=SingletonJedisPool.getJedisPool().getResource();
		jedis.flushDB();
		SingletonJedisPool.getJedisPool().returnResource(jedis);
	}

	public boolean containsKey(K key) {
		Jedis jedis=SingletonJedisPool.getJedisPool().getResource();
		boolean is = jedis.exists(redisSerializer.serialize(key.toString()));
		SingletonJedisPool.getJedisPool().returnResource(jedis);
		return is;
	}
	

}
