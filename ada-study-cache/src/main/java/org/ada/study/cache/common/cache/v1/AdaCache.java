package org.ada.study.cache.common.cache.v1;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.ada.study.cache.common.cache.v1.entity.EntityCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**  
 * Filename: MdCache.java  <br>
 *
 * Description:  自定义缓存 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年3月4日 <br>
 *
 *  
 */

public class AdaCache {
	private static Log logger = LogFactory.getLog(AdaCache.class);

	private final static boolean is_cache = true;//是否缓存

	private final static boolean is_log = false;

	//该缓存，由于JVM垃圾回收，偶尔会为空，可以改为concurrentHashMap
	private static  Map<String, EntityCache> cache = new WeakHashMap<String, EntityCache>(2000);
	
	private AdaCache(){}

	private static long sleep_time = 1000*5;//缓存时间毫秒

	private static long max_size = 100*5;//千为单位，容器最大值5千个对象
	
	/**
	 * 获取缓存值
	 * @param key
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年3月4日
	 */
	private static Object getObject(String key,Long cacheTimes){
		//检查缓存数量
		checkMax();
		if(null!=cache&&cache.size()>0&&cache.containsKey(key)){
			EntityCache object = cache.get(key);
			if(is_log){
				log("命中[key:"+key+",value:"+object+"]缓存");
			}
			if(System.currentTimeMillis()-object.getNow_time()>=(null!=cacheTimes?cacheTimes:sleep_time)){
				if(is_log){
					log("超时删除中.....[key:"+key+",value:"+object+"]缓存");
				}
				cache.remove(key);
				return null;
			}
			return null==object?null:object.getObject();
		}
		return null;
	}
	private static void log(String message){
		if(is_log){
			logger.error(message);
		}
	}
	/**
	 * 设置缓存值
	 * @param key
	 * @param object
	 * @author: CZD  
	 * @Createtime: 2016年3月4日
	 */
	private static void setObject(String key,Object object){
		EntityCache cache_entity = new EntityCache();
		cache_entity.setNow_time(System.currentTimeMillis());
		cache_entity.setObject(object);
		log("设置中.....[key:"+key+",value:"+object+"]缓存");
		cache.put(key, cache_entity);
	}
	/**
	 * 防止缓存过大
	 * 
	 * @author: CZD  
	 * @Createtime: 2016年3月4日
	 */
	private static void checkMax(){
		if(null!=cache&&cache.size()>max_size){
			//删除个数
			long remove_size = cache.size()-max_size;
			//删除行号
			long remove_row = 0;

			Iterator<Map.Entry<String, EntityCache>> it = cache.entrySet().iterator();

			while(it.hasNext()){  
				if(remove_size==remove_row){
					break;
				}
				Map.Entry<String, EntityCache> entry=it.next();  
				String key=entry.getKey();
				log("超出最大值，删除中.....[key:"+key+"]缓存");
				it.remove();        //OK   
				remove_row++;
			}  

		}  
	}
	//加锁，提高性能【在资源竞争不是很激烈的情况下，Synchronized的性能要优于ReetrantLock，但是在资源竞争很激烈的情况下，Synchronized的性能会下降几十倍，但是ReetrantLock的性能能维持常态】
	private static ReentrantLock lock = new ReentrantLock();
	
	public static <T> T getCache(String key,QueryFunction<T> function){
		return getCache(key, function, null);
	}
	/**
	 * 外部获取缓存数据
	 * @param key
	 * @param function
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年3月4日
	 */
	public static <T> T getCache(String key,QueryFunction<T> function,Long cacheTimes){
		T object = null;
			if(is_cache){
				try {
					lock.lock();
					object = (T)getObject(key,null==cacheTimes?sleep_time:cacheTimes);
					if(null==object){
						long startTime = System.currentTimeMillis();
						object = function.missData();
						setObject(key, object);
						log("重新获取原始数据，耗时.....[key:"+key+"]["+(System.currentTimeMillis()-startTime)+"]");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}else{
				object = function.missData();
			}
		
		
		return object;
	}
}
