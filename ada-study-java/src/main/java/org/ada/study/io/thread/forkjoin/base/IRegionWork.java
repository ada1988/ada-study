package org.ada.study.io.thread.forkjoin.base;

import java.util.List;

/**  
 * Filename: IRegionWork.java  <br>
 *
 * Description: 分区作业  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月5日 <br>
 *
 *  
 */

public interface IRegionWork<T> {
	/**
	 * 分区后的具体作业
	 * 
	 * @param data
	 * @author: CZD  
	 * @Createtime: 2017年7月5日
	 */
	public void doWork(List<T> data);
}
