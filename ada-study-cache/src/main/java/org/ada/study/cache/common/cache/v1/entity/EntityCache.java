package org.ada.study.cache.common.cache.v1.entity;
/**  
 * Filename: EntityCache.java  <br>
 *
 * Description:  缓存内部实体 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年3月4日 <br>
 *
 *  
 */

public class EntityCache {
	private long now_time;
	private Object object;
	public long getNow_time() {
		return now_time;
	}
	public void setNow_time(long now_time) {
		this.now_time = now_time;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
