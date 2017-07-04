package org.ada.study.cache.common.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.redisson.RedissonClient;
import org.redisson.core.RLock;
import org.redisson.core.RReadWriteLock;
import org.redisson.spring.cache.NullValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisCluster;

public class RedisCacheClient{
	
	private static Logger logger = LoggerFactory.getLogger(RedisCacheClient.class);

	private JedisCluster redisCluster;
	
	private RedissonClient redisson;
	
	private String lockName;
	
	public RedisCacheClient(JedisCluster redisCluster){
		this.redisCluster = redisCluster;
	}
	
	public RedisCacheClient(JedisCluster redisCluster,RedissonClient redisson,String lockName){
		this.redisCluster = redisCluster;
		this.redisson = redisson;
		this.lockName = lockName;
	}

	public <T> void setValue(String key, T value, int expire)
			throws Exception {
		redisCluster.set(key.getBytes(), ObjectsTranscoder.serialize(value));
		if(expire > 0){
			redisCluster.expire(key.getBytes(), expire);
		}
	}
	
	public <T> void setValueWithLock(String key, T value, int expire,String lock)
			throws Exception {
		final RReadWriteLock rwlock = redisson.getReadWriteLock(StringUtils.isEmpty(lock)?lockName:lock);
		final RLock writeLock = rwlock.writeLock();
		writeLock.lock();
		if(!exists(key)){
			setValue(key, value, expire);
			logger.info("Add FundList Data Use DistributeLock");
		}
		writeLock.unlock();
	}
	
	public <T,K> void hsetValue(String key,T field,K value,int expire) throws Exception {
		redisCluster.hset(key.getBytes(), ObjectsTranscoder.serialize(field), ObjectsTranscoder.serialize(value));
		if(expire > 0){
			redisCluster.expire(key.getBytes(), expire);
		}
	}
	
	public <T,K> K hgetValue(String key,T field) throws Exception {
		byte[] in = redisCluster.hget(key.getBytes(), ObjectsTranscoder.serialize(field));
		K list = ObjectsTranscoder.deserialize(in);  
		return list;
	}

	public <T> void deleteValue(String key) throws Exception {
		redisCluster.del(key.getBytes());
	}

	public boolean exists(String key) throws Exception {
		return redisCluster.exists(key.getBytes());
	}

	public <T> T getValue(String key) throws Exception {
		byte[] in = redisCluster.get(key.getBytes());
		if(in == null){
			return null;
		}
		T list = ObjectsTranscoder.deserialize(in);
		return list;
	}
	
	public <T> T getValueWithLock(String key,String lock) throws Exception {
		final RReadWriteLock rwlock = redisson.getReadWriteLock(StringUtils.isEmpty(lock)?lockName:lock);
		final RLock readLock = rwlock.readLock();
		readLock.lock();
		T t = getValue(key);
		readLock.unlock();
		return t;
	}

	public long ttl(String key){
		return redisCluster.ttl(key.getBytes());
	}
	
	static class ObjectsTranscoder {
		
		public static <M> byte[] serialize(M value) throws Exception {
			if (value == null) {
				//throw new NullPointerException("Can't serialize null");
				value = (M)"null";
			}
			byte[] result = null;
			ByteArrayOutputStream bos = null;
			ObjectOutputStream os = null;
			try {
				bos = new ByteArrayOutputStream();
				os = new ObjectOutputStream(bos);
				M m = (M) value;
				os.writeObject(m);
				os.close();
				bos.close();
				result = bos.toByteArray();
			} catch (IOException e) {
				throw new IllegalArgumentException("Non-serializable object", e);
			} finally {
				if(os != null) 
				os.close();
				if(bos != null)
				bos.close();
			}
			return result;
		}

		@SuppressWarnings("unchecked")
		public static <T> T deserialize(byte[] in) throws Exception {
			T result = null;
			ByteArrayInputStream bis = null;
			ObjectInputStream is = null;
			try {
				if (in != null) {
					bis = new ByteArrayInputStream(in);
					is = new ObjectInputStream(bis);
					result = (T) is.readObject();
					is.close();
					bis.close();
				}
			} catch (IOException e) {
				logger.error("",e);
			} catch (ClassNotFoundException e) {
				logger.error("",e);
			} finally {
				if(is != null)
				is.close();
				if(bis != null)
				bis.close();
			}
			return result;
		}
	}

	public JedisCluster getRedisCluster() {
		return redisCluster;
	}

	public void setRedisCluster(JedisCluster redisCluster) {
		this.redisCluster = redisCluster;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public RedissonClient getRedisson() {
		return redisson;
	}

	public void setRedisson(RedissonClient redisson) {
		this.redisson = redisson;
	}
	
}
