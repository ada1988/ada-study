package org.ada.study.tools.model.state;
/**  
 * Filename: ResultBean.java  <br>
 *
 * Description: 返回值  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月12日 <br>
 *
 *  
 */

public class ResultBean {
	/**
	 * 返回信息描述
	 */
	private String mgs;
	/**
	 * 返回信息编码:0:成功;1:失败;
	 */
	private String code;

	private Object data;
	
	private boolean fail=true;

	public ResultBean(){

	}

	public ResultBean(String code,String mgs,Object data){
		this.code = code;
		this.mgs = mgs;
		this.data = data;
	}

	public String getMgs() {
		return mgs;
	}
	public void setMgs(String mgs) {
		this.mgs = mgs;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String toString(){
		return "[code:"+code+","+"mgs:"+mgs+",fail:"+fail+"]";
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public boolean isFail() {
		return fail;
	}

	public void setFail(boolean fail) {
		this.fail = fail;
	}
}
