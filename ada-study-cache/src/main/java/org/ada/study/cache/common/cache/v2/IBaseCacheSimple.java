package org.ada.study.cache.common.cache.v2;
/**  
 * Filename: IBaseCache.java  <br>
 *
 * Description: 缓存基础接口  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月22日 <br>
 *
 *  
 */

public interface IBaseCacheSimple<K,V> {
	/**
	 * 获取缓存
	 * @param k
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public V get(K k);
	
	/**
	 * 根据key删除缓存
	 * @param k
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public void remove(K k);
	
	/**
	 * 存放缓存
	 * @param k
	 * @param v
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public void put(K k,V v);
	
	/**
	 * 清空缓存
	 * 
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public void clear();
	
}
