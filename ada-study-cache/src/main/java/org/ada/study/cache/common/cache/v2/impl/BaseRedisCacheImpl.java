package org.ada.study.cache.common.cache.v2.impl;

import org.ada.study.cache.common.cache.v2.IBaseCacheSimple;
import org.ada.study.cache.common.cache.v2.IBaseContainSimple;
import org.ada.study.cache.common.cache.v2.IQueryFunction;
import org.ada.study.cache.common.cache.v2.support.redis.SupportRedisContainSimple;

/**  
 * Filename: BaseCache.java  <br>
 *
 * Description: redis实现缓存  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月22日 <br>
 *
 *  
 */

public class BaseRedisCacheImpl<K, V> implements IBaseCacheSimple<K, V>{
	
	private final String REDIS_KEY = "default_ada";
	
	private IBaseContainSimple<K, V> cacheContain = null;
	
	private IQueryFunction<K, V> function = null;

	/**
	 * 默认
	 */
	public BaseRedisCacheImpl(){
		cacheContain = new SupportRedisContainSimple<K, V>(REDIS_KEY);
	}
	
	public BaseRedisCacheImpl(String redisKey){
		cacheContain = new SupportRedisContainSimple<K, V>(redisKey);
	}
	
	public BaseRedisCacheImpl(IQueryFunction<K, V> function,String redisKey){
		cacheContain = new SupportRedisContainSimple<K, V>(redisKey);
		this.function = function;
	}
	

	public V get(K k) {
		if(cacheContain.containsKey(k)){
			return cacheContain.get(k);
		}else{
			if(null==function){
				return null;
			}
			V v = function.query(k);
			if(null==v){
				return null;
			}
			cacheContain.put(k,v);
			return v;
		}
	}

	public void remove(K k) {
		cacheContain.remove(k);
	}

	public void put(K k, V v) {
		cacheContain.put(k,v);
	}

	public void clear() {
		cacheContain.clear();
	}

}
