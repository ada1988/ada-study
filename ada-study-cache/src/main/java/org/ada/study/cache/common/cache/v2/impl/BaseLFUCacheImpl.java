package org.ada.study.cache.common.cache.v2.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.ada.study.cache.common.cache.v2.IBaseLFUCache;
import org.ada.study.cache.common.cache.v2.IQueryFunction;
import org.ada.study.cache.common.cache.v2.entity.EntityLFUCache;

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

public class BaseLFUCacheImpl<K, V> implements IBaseLFUCache<K, V>{
	//加锁，提高性能【在资源竞争不是很激烈的情况下，Synchronized的性能要优于ReetrantLock，但是在资源竞争很激烈的情况下，Synchronized的性能会下降几十倍，但是ReetrantLock的性能能维持常态】
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private Map<K, EntityLFUCache<V>> cacheMap = new WeakHashMap<K, EntityLFUCache<V>>();
	
	private final long MAX_SIZE = 1000*5;//千为单位，容器最大值5千个对象
	
	private long TIMES = 100*5;//缓存时间（毫秒）

	private IQueryFunction<K,V> function = null;
	
	public BaseLFUCacheImpl(){
	}
	
	public BaseLFUCacheImpl(IQueryFunction<K,V> function){
		this.function = function;
	}
	
	public V get(K k) {
		if(cacheMap.containsKey(k)){
			EntityLFUCache<V> entity = cacheMap.get(k);
			//超时数据，进行清理
			if(checkOverTimes(entity, k)){
				cacheMap.remove(k);
				return null;
			}
			return entity.getV();
		}else{
			if(null==function){
				return null;
			}
			//如果存在自实现方法，填充未知数据
			V v = function.query(k);
			if(null!=v){
				try {
					lock.writeLock().lock();
					EntityLFUCache<V> entityLFUCache = new EntityLFUCache<V>();
					entityLFUCache.setNow_time(System.currentTimeMillis());
					entityLFUCache.setV(v);
					cacheMap.put(k,entityLFUCache);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					lock.writeLock().unlock();
				}
				return v;
			}
		}
		return null;
	}

	public void remove(K k) {
		cacheMap.remove(k);
	}

	public void put(K k, V v) {
		try {
			lock.writeLock().lock();
			EntityLFUCache<V> entityLFUCache = new EntityLFUCache<V>();
			entityLFUCache.setNow_time(System.currentTimeMillis());
			entityLFUCache.setV(v);
			cacheMap.put(k,entityLFUCache);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.writeLock().unlock();
		}
	}

	public void clear() {
		cacheMap.clear();
	}

	public void clearOverFlow() {
		if(null!=cacheMap&&cacheMap.size()>MAX_SIZE){
			//删除个数
			double removeSize = Math.floor(cacheMap.size()*0.2);
			//删除行号
			double removeRow = 0;

			try {
				lock.writeLock().lock();
				
				Iterator<Map.Entry<K, EntityLFUCache<V>>> it = cacheMap.entrySet().iterator();

				while(it.hasNext()){  
					if(removeRow==removeSize){
						break;
					}
					//Map.Entry<K, EntityLFUCache<V>> entry=it.next();  
					it.remove();        //OK   
					removeRow++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}  finally{
				lock.writeLock().unlock();
			}
		}
	}

	public int getSize() {
		return cacheMap.size();
	}

	public boolean checkOverTimes(EntityLFUCache<V> entity,K k) {
		//超时数据，进行清理
		if(System.currentTimeMillis()-entity.getNow_time()>TIMES){
			return true;
		}
		return false;
	}


}
