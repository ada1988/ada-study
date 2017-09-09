package org.ada.study.tools.api.model;
/**  
 * Filename: RespBase.java  <br>
 *
 * Description: 响应  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月16日 <br>
 *
 *  
 */

public class RespBase<T> {
	private Integer code;//状态码
	private String msg;//描述信息
	private T data;//数据
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
