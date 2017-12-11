package org.ada.study.flow.common.vo;

import java.io.Serializable;
/**
 * 缓存信息
 * @author yingwei
 *
 */
public class CacheInfoVo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5637597333118365971L;
	/**
	 * 缓存名称
	 */
	private String name;
	/**
	 * 缓存具体实现
	 */
	private String impl;
	/**
	 * 获取时间
	 */
	private long overdueTime;


	

	public String getImpl() {
		return impl;
	}

	public void setImpl(String impl) {
		this.impl = impl;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOverdueTime() {
		return overdueTime;
	}

	public void setOverdueTime(long overdueTime) {
		this.overdueTime = overdueTime;
	}
	
	

}
