package org.ada.study.storm.mysql.em;
/**  
 * Filename: LogFieldRalation.java  <br>
 *
 * Description:  nginx log日志格式，通过分隔符分隔，对应的索引位置 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public enum LogFieldRalationEM {
	
	LOG_remote_addr(0,"ip","IP地址"),
	LOG_request_uri(1,"req_url","req_url"),
	LOG_http_referer(2,"req_refer","req_refer"),
	LOG_time_local(3,"req_time","req_time"),
	LOG_request_body(4,"post_params","post_params"),
	LOG_http_cookie_session_id1(5,"session_id_v1","session_id_v1"),
	LOG_http_cookie_session_id2(5,"session_id_v2","session_id_v2"),
	LOG_status(6,"status","status");
	
	private LogFieldRalationEM(int logIndex,String fieldName,String fieldDesc){
		this.logIndex = logIndex;
		this.fieldName = fieldName;
		this.fieldDesc = fieldDesc;
	}
	
	private int logIndex;
	private String fieldName;
	private String fieldDesc;
	public int getLogIndex() {
		return logIndex;
	}
	public void setLogIndex(int logIndex) {
		this.logIndex = logIndex;
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
