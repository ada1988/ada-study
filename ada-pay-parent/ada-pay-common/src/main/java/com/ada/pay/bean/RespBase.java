package com.ada.pay.bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**  
 * Filename: RespBase.java  <br>
 *
 * Description: 响应  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime:  2020年12月02日 <br>
 *
 *  
 */

public class RespBase<T> {
	private String code;//返回编码
	private String msg;//网关处理消息
	private T resData;
	/**  
	 * @Title:  getMsg <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getMsg() {
		return msg;
	}
	/**  
	 * @Title:  setMsg <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**  
	 * @Title:  getResData <BR>  
	 * @Description: please write your description <BR>  
	 * @return: T <BR>  
	 */
	public T getResData() {
		return resData;
	}
	/**  
	 * @Title:  setResData <BR>  
	 * @Description: please write your description <BR>  
	 * @return: T <BR>  
	 */
	public void setResData(T resData) {
		this.resData = resData;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
