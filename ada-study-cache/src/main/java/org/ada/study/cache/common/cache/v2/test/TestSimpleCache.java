package org.ada.study.cache.common.cache.v2.test;

import java.util.HashMap;
import java.util.Map;

import org.ada.study.cache.common.cache.v2.IBaseCache;
import org.ada.study.cache.common.cache.v2.IBaseCacheSimple;
import org.ada.study.cache.common.cache.v2.IQueryFunction;
import org.ada.study.cache.common.cache.v2.impl.BaseCacheImpl;
import org.ada.study.cache.common.cache.v2.impl.BaseRedisCacheImpl;

/**  
 * Filename: TestCache.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月22日 <br>
 *
 *  
 */

public class TestSimpleCache {
	
	static Map<String, Object> maps = new HashMap<String, Object>();
	
	static{
		maps.put("key1", "value1");
		maps.put("key2", "value2");
		maps.put("key3", "value3");
	}
	
	public static Object queryDbBykey(String key){
		return maps.get(key);
	}
	/**
	 * 简单缓存测试
	 * 
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public static void testSampleCache(){
		IBaseCacheSimple<String, Object> cache = new BaseCacheImpl<String, Object>(new IQueryFunction<String, Object>() {
			public Object query(java.lang.String k) {
				return TestSimpleCache.queryDbBykey(k);
			}
		});
		System.out.println(cache.get("key1"));
		System.out.println(cache.get("key5"));
	}
	/**
	 * redis缓存测试
	 * 
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public static void testRedisCache(){
		final String redisKey = "ada";
		IBaseCacheSimple<String, Object> cache = new BaseRedisCacheImpl<String, Object>(new IQueryFunction<String, Object>() {
			public Object query(java.lang.String k) {
				return TestSimpleCache.queryDbBykey(k);
			}
		},redisKey);
		System.out.println(cache.get("key1"));
		System.out.println(cache.get("key5"));
	}
	
	public static void main(String[] args) {
		TestSimpleCache.testSampleCache();
	}
}
