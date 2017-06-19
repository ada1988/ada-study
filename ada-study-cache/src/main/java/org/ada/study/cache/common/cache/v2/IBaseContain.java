package org.ada.study.cache.common.cache.v2;



/**  
 * Filename: IBaseCache.java  <br>
 *
 * Description: 缓存容器基础接口  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月22日 <br>
 *
 *  
 */

public interface IBaseContain<K, V, S> extends IBaseCache<K, V, S>{
	/**
	 * 是否包含key
	 * @param key
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public boolean containsKey(K key);
}
