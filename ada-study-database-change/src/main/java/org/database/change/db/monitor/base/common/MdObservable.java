package org.database.change.db.monitor.base.common;
/**  
 * Filename: MdObservable.java  <br>
 *
 * Description:  观察者 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月19日 <br>
 *
 *  
 */

public interface MdObservable<T> {
	/**
	 * 观察者通知方法
	 * @param metadata  元数据
	 * @author: CZD  
	 * @Createtime: 2017年4月19日
	 */
	public void callBack(T metadata);
}
