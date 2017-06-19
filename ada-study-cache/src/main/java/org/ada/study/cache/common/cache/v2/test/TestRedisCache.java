package org.ada.study.cache.common.cache.v2.test;

import java.util.HashMap;
import java.util.Map;

import org.ada.study.cache.common.cache.v2.IBaseTemplateCache;
import org.ada.study.cache.common.cache.v2.IQueryFunction;
import org.ada.study.cache.common.cache.v2.impl.BaseRedisTemplateCacheImpl;

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

public class TestRedisCache {
	
	static Map<String, Object> maps = new HashMap<String, Object>();
	
	static{
		maps.put("key1", "value1");
		maps.put("key2", "value2");
		maps.put("key3", "value3");
		maps.put("key4", "value1");
		maps.put("key5", "value2");
		maps.put("key6", "value3");
	}
	
	public static Object queryDbBykey(String key){
		return maps.get(key);
	}
	
	public static Object queryDbBykey(TestSearch key){
		TestSearch search = new TestSearch(); 
		search.setName("name_"+key);
		search.setTitle("title_key"+key);
		return search;
	}
	/**
	 * redis缓存测试
	 * 
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public static void testRedisCache(){
		TestSearch search = new TestSearch();
		final String redisKey = "ada";
		IBaseTemplateCache<String, Object,TestSearch> cache = new BaseRedisTemplateCacheImpl<String, Object,TestSearch>(new IQueryFunction<TestSearch, Object>() {
			public Object query(TestSearch search) {
				return TestRedisCache.queryDbBykey(search);
			}
		},redisKey);
		search.setName("test1");
		search.setTitle("title1");
		cache.put("test1", search);
		search = new TestSearch();
		search.setName("test2");
		search.setTitle("title2");
		cache.put("test2", search);
		
		System.out.println(cache.get("test1",search));
		System.out.println(cache.get("key5",search));
		System.out.println(cache.get("key333",search));
	}
	
	public static void main(String[] args) {
		TestRedisCache.testRedisCache();
	}
	
}
