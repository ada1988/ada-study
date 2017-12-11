package org.ada.study.storm.mysql.em;
/**  
 * Filename: LogFieldRalation.java  <br>
 *
 * Description:  数据库对应的关系 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public enum  ProductFiledsDbEM{
	
	/**
	 * ProductFiledsDbEM 对应ProductFiledsFlowEM的顺序
	 * 
	 * 存储时，数据表，字段位置
	 */
	product_type(new int[]{0},"product_type","product_type"),
	product_id(new int[]{3},"product_id","product_id"),
	look_time(new int[]{2},"look_time","look_time"),
	user_mobile(new int[]{4,5},"user_mobile","user_mobile"),
	user_ip(new int[]{0},"user_ip","user_ip"),
	residence_times(new int[]{0},"residence_times","residence_times");
	private ProductFiledsDbEM(int[] preIndexs,String fieldName,String fieldDesc){
		this.preIndexs = preIndexs;
		this.fieldName = fieldName;
		this.fieldDesc = fieldDesc;
	}
	
	private int[] preIndexs;
	private String fieldName;
	private String fieldDesc;
	public int[] getPreIndexs() {
		return preIndexs;
	}
	public void setPreIndexs(int[] preIndexs) {
		this.preIndexs = preIndexs;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
}
