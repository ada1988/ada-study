package com.miduo.channel.account.common.constant;


/**  
 * Filename: EAccountStatus.java  <br>
 *
 * Description:  用户状态 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月5日 <br>
 *
 *  
 */

public enum EAccountStatus {
	
	启用("启用",0),
	禁用("禁用",1);
	
	private String name;
	private Integer value;
	
	public static EAccountStatus get(int value){
		EAccountStatus[] statues = EAccountStatus.values();
		for(EAccountStatus status : statues){
			if(status.value.equals(value)){
				return status;
			}
		}
		return null;
	}
	
	private EAccountStatus(String name,Integer value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
