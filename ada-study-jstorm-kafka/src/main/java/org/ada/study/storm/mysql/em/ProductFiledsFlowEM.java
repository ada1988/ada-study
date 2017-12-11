package org.ada.study.storm.mysql.em;
/**  
 * Filename: LogFieldRalation.java  <br>
 *
 * Description:  产品流程中字段（由用户关系节点过来的流程字段） <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public enum  ProductFiledsFlowEM{
	
	/**
	 * ProductFiledsEM 对应LogFieldRalationEM的顺序
	 */
	ip(0,0,"ip","IP地址"),
	req_url(1,1,"req_url","req_url"),
	req_time(3,2,"req_time","req_time"),
	post_params(4,3,"post_params","post_params"),
	session_id_v1(5,4,"session_id_v1","session_id_v1"),
	session_id_v2(6,5,"session_id_v2","session_id_v2");
	
	private ProductFiledsFlowEM(int preIndex,int nextIndex,String fieldName,String fieldDesc){
		this.preIndex = preIndex;
		this.nextIndex = nextIndex;
		this.fieldName = fieldName;
		this.fieldDesc = fieldDesc;
	}
	
	private int preIndex;
	private int nextIndex;
	private String fieldName;
	private String fieldDesc;
	public int getPreIndex() {
		return preIndex;
	}
	public void setPreIndex(int preIndex) {
		this.preIndex = preIndex;
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
	public int getNextIndex() {
		return nextIndex;
	}
	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}
}
