package org.ada.study.cache.common.cache.v2.impl;

import java.util.Map;
import java.util.WeakHashMap;

import org.ada.study.cache.common.cache.v2.IBaseCacheSimple;
import org.ada.study.cache.common.cache.v2.IQueryFunction;

/**  
 * Filename: BaseCache.java  <br>
 *
 * Description: 简单Map实现缓存  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月22日 <br>
 *
 *  
 */

public class BaseCacheImpl<K, V> implements IBaseCacheSimple<K, V>{
	
	private Map<K, V> cacheMap = new WeakHashMap<K, V>();
	
	private IQueryFunction<K, V> function = null;

	public BaseCacheImpl(){}
	
	public BaseCacheImpl(IQueryFunction<K, V> function){
		this.function = function;
	}
	
	public V get(K k) {
		if(cacheMap.containsKey(k)){
			return cacheMap.get(k);
		}else{
			if(null==function){
				return null;
			}
			V v = function.query(k);
			if(null==v){
				return null;
			}
			cacheMap.put(k,v);
			return v;
		}
	}

	public void remove(K k) {
		cacheMap.remove(k);
	}

	public void put(K k, V v) {
		cacheMap.put(k,v);
	}

	public void clear() {
		cacheMap.clear();
	}

}
