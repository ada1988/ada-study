package org.ada.study.tools.task.common;

import java.util.List;

/**  
 * Filename: ISchedulingCacheStrategy.java  <br>
 *
 * Description: 定时更新缓存 策略的接口  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月7日 <br>
 *
 *  
 */

public interface ISchedulingCacheStrategy<Result,Request> {
	
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
	public List<Request> warpRequests(String fundid,Boolean isFirst);
	
	/**
	 * 是否跳过该步骤(集群部署项目，避免过多次数更新缓存)
	 * @param request
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年8月29日
	 */
	public boolean isPass(Request request);
	
}
