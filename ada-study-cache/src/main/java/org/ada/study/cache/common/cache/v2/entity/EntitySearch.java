package org.ada.study.cache.common.cache.v2.entity;
/**  
 * Filename: EntityCache.java  <br>
 *
 * Description: 键实体  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年3月4日 <br>
 *
 *  
 */

public class EntitySearch<S> {
	private String key;
	private S search;
	public S getSearch() {
		return search;
	}
	public void setSearch(S search) {
		this.search = search;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
