package org.ada.study.cache.common.cache.v3;
/**  
 * Filename: ICacheUpdate.java  <br>
 *
 * Description: 缓存更新  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月16日 <br>
 *
 *  
 */

public interface ICacheUpdate<Result> {
	/**
	 * 更新缓存
	 * @param param
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年7月16日
	 */
	public Integer updateCache(Result result);
}
