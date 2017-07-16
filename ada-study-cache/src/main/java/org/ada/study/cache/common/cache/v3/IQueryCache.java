package org.ada.study.cache.common.cache.v3;
/**  
 * Filename: IMissingCache.java  <br>
 *
 * Description: 缓存失败，回源方案  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月16日 <br>
 *
 *  
 */

public interface IQueryCache<Result,Param> {
	/**
	 * 缓存失败，回源方案
	 * @param param
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年7月16日
	 */
	public Result queryData(Param param);
}
