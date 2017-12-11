package org.ada.study.flow.common.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
/**
 * 基础响应类
 * @author yingwei
 *
 */
public class Resp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111632514336370224L;
	/**
	 * 响应状态码
	 */
	private int state=Code.TRUE;
	/**
	 * 响应消息
	 */
	private String msg;
	/**
	 * 数据
	 */
	private Object data;
	
	
	

	public Resp() {
		super();
	}

	public Resp(int state){
		this(state,null,null);
	}
	
	public Resp(int state,String msg){
		this(state,msg,null);
	}
	
	public Resp(int state,Object data){
		this(state,null,data);
	}
	
	
	
	
	public Resp(int state, String msg, Object data) {
		super();
		this.state = state;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 返回成功
	 * @param data
	 * @return
	 */
	public static Resp OK(){
		return new Resp(Code.TRUE,null,null);
	}
	public static <T> Resp OK(T data){
		return OK(null, data);
	}
	
	public static Resp OK(String msg){
		return new Resp(Code.TRUE,msg,null);
	}
	
	
	
	public static <T> Resp OK(String msg,T data){
		return new Resp(Code.TRUE,msg,data);
	}
	
	
	
	
	
	/**
	 * 返回错误
	 * @param msg
	 * @return
	 */
	public static Resp ERR(){
		return ERR(Code.FALSE,null,null);
	}
	public static Resp ERR(String msg){
		return ERR(Code.FALSE,msg,null);
	}
	
	public static Resp ERR(int state,String msg){
		return ERR(state,msg,null);
	}
	
	public static <T> Resp ERR(int state,String msg,T data){
		return new Resp(state, msg,data);
	}
	
	public static Resp ERR(BindingResult br){
		List<ObjectError> oes=br.getAllErrors();
		StringBuffer sb=new StringBuffer();
		int index=1;
		for(ObjectError oe:oes){
			sb.append(index).append("").append(oe.getDefaultMessage()).append("\r\n");
			index++;
		}
		return ERR(sb.toString());
	}
	

	
	public static Resp R(int state){
		return R(state, null, null);
	}
	public static <T> Resp R(int state,T data){
		return R(state, null, data);
	}
	
	public static Resp R(int state,String msg){
		return R(state, msg, null);
	}
	
	
	
	public static <T> Resp R(int state,String msg,T data){
		return new Resp(state,msg,data);
	}
	
	
	
	
	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
