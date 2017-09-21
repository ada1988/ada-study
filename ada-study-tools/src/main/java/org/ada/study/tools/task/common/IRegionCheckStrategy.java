package org.ada.study.tools.task.common;
/**  
 * Filename: IRegionStrategy.java  <br>
 *
 * Description: 集群间判断区域是否执行  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月11日 <br>
 *
 *  
 */

public interface IRegionCheckStrategy {
	/**
	 * 是否跳过
	 * @param region
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月11日
	 */
	public Boolean isPass(Integer region);
	
	/**
	 * 删除分布式键
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年9月11日
	 */
	public void removeDistributedKey(Integer region);
}
