package org.ada.study.cache.common.cache.v2.entity;
/**  
 * Filename: EntityCache.java  <br>
 *
 * Description: LFU最少使用算法实体  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年3月4日 <br>
 *
 *  
 */

public class EntityLFUCache<V> {
	private long now_time;
	private V v;
	public long getNow_time() {
		return now_time;
	}
	public void setNow_time(long now_time) {
		this.now_time = now_time;
	}
	public V getV() {
		return v;
	}
	public void setV(V v) {
		this.v = v;
	}
}
