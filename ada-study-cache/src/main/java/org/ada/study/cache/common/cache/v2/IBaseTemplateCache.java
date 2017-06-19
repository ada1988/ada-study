package org.ada.study.cache.common.cache.v2;
/**  
 * Filename: IBaseTemplateCache.java  <br>
 *
 * Description:  缓存自定义模板 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月23日 <br>
 *
 *  
 */

public interface IBaseTemplateCache<K, V, S>{
	
	/**
	 * 携带参数，获取缓存
	 * @param k
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public V get(K k,S s);
	
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
