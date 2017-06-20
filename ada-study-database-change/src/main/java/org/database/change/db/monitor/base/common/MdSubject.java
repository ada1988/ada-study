package org.database.change.db.monitor.base.common;
/**  
 * Filename: MdSubject.java  <br>
 *
 * Description: 主题 （拉模式，全通知） <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月19日 <br>
 *
 *  
 */

public interface MdSubject<T> {
	
	/**
	 * 订阅主题
	 * @param obServable
	 * @author: CZD  
	 * @Createtime: 2017年4月19日
	 */
	public void subscribe(MdObservable<T> obServable);
	
	/**
	 * 取消订阅
	 * @param obServable
	 * @author: CZD  
	 * @Createtime: 2017年4月19日
	 */
	public void unSubscribe(MdObservable<T> obServable);
	
	/**
	 * 通知观察者
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年4月19日
	 */
	public void notifies(T metadata);
	
	/**
	 * 主题变化类
	 * @param metadata
	 * @author: CZD  
	 * @Createtime: 2017年4月19日
	 */
	public void subjectChange(T metadata);
}
