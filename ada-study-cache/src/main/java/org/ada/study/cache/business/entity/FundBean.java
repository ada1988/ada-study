package org.ada.study.cache.business.entity;

import java.io.Serializable;

/**  
 * Filename: FundBean.java  <br>
 *
 * Description:  基金实体 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月19日 <br>
 *
 *  
 */

public class FundBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	public FundBean(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "FundBean [id=" + id + ", name=" + name + "]";
	}
}
