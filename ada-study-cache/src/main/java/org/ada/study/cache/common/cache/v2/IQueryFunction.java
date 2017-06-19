package org.ada.study.cache.common.cache.v2;



/**  
 * Filename: QueryFunction.java  <br>
 *
 * Description:  查询接口 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年3月4日 <br>
 *
 *  
 */

public interface IQueryFunction<K,V> {
	
	V query(K key);
}
