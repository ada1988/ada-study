package org.ada.study.cache.common.cache.v2;

import org.ada.study.cache.common.cache.v2.entity.EntityLFUCache;

/**  
 * Filename: IBaseLFUCache.java  <br>
 *
 * Description: 最近最少使用算法  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年6月22日 <br>
 *
 *  
 */

public interface IBaseLFUCache<K, V> extends IBaseCacheSimple<K, V>{
	/**
	 * 清理溢出数据[清理容器总量的20%]
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public void clearOverFlow();
	
	/**
	 * 缓存大小
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public int getSize();
	
	/**
	 * 检查是否超时
	 * @param v
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年6月22日
	 */
	public boolean checkOverTimes(EntityLFUCache<V> entity,K k);
	
	
}
