package org.ada.study.tools.task.raw;

import java.util.List;

/**  
 * Filename: ISchedulingCacheReadAndWriteStrategy.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月11日 <br>
 *
 *  
 */

public interface ISchedulingCacheReadAndWriteStrategy<Result,Request> {
	/**
	 * 查询缓存
	 * @param request
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年8月7日
	 */
	public Result queryCache(Request request);
	
	/**
	 * 更新缓存
	 * @param request
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年8月7日
	 */
	public Result updateCache(Request request);
	
	/**
	 * 参数装载 （拼装不同参数，处理不同请求）
	 * @param fundid
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年8月29日
	 */
	public List<Request> warpRequests(String fundid);
	
}
