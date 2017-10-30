package org.ada.study.storm.mysql.em;
/**  
 * Filename: LogFieldRalation.java  <br>
 *
 * Description:  tbl_user_relation 字段 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public enum UserRelationDbFieldsEM {
	/**
	 * 保证顺序
	 */
	user_ip(new int[]{0},"user_ip","user_ip"),
	session_id(new int[]{5,6},"session_id","session_id"),
	user_mobile(new int[]{4},"user_mobile","user_mobile"),
	session_version(new int[]{5,6},"session_version","session_version"),
	info(new int[]{0,1,2,3,4,5,6,7},"info","info");
	
	private UserRelationDbFieldsEM(int[] preIndexs,String fieldName,String fieldDesc){
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
