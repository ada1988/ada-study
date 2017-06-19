package org.ada.study.cache.common.cache.v2.impl;

import org.ada.study.cache.common.cache.v2.IBaseContain;
import org.ada.study.cache.common.cache.v2.IBaseTemplateCache;
import org.ada.study.cache.common.cache.v2.IQueryFunction;
import org.ada.study.cache.common.cache.v2.support.redis.SupportRedisContain;

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

public class BaseRedisTemplateCacheImpl<K, V, S> implements IBaseTemplateCache<K, V, S>{
	
	private final String REDIS_KEY = "default_ada";
	
	private IBaseContain<K, V, S> cacheContain = null;
	
	private IQueryFunction<S,V> function = null;

	/**
	 * 默认
	 */
	public BaseRedisTemplateCacheImpl(){
		cacheContain = new SupportRedisContain<K, V, S>(REDIS_KEY);
	}
	
	public BaseRedisTemplateCacheImpl(String redisKey){
		cacheContain = new SupportRedisContain<K, V, S>(redisKey);
	}
	
	public BaseRedisTemplateCacheImpl(IQueryFunction<S,V> function,String redisKey){
		cacheContain = new SupportRedisContain<K, V, S>(redisKey);
		this.function = function;
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

	public V get(K k, S s) {
			if(cacheContain.containsKey(k)){
				return cacheContain.get(k);
			}else{
				if(null==function){
					return null;
				}
				V v = function.query(s);
				if(null==v){
					return null;
				}
				cacheContain.put(k,v);
				return v;
			}
	}
}
