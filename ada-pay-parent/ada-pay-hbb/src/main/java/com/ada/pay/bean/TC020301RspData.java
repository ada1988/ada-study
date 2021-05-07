package com.ada.pay.bean;

/**   
 * 处理日志查询
 */
public class TC020301RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String reqContent; //原始请求报文	Y
	
	private String rspContent; //原始响应报文	Y
	
	public String getBizFlag() {
		return bizFlag;
	}

	public void setBizFlag(String bizFlag) {
		this.bizFlag = bizFlag;
	}

	public String getBizMsg() {
		return bizMsg;
	}

	public void setBizMsg(String bizMsg) {
		this.bizMsg = bizMsg;
	}

	public String getReqContent() {
		return reqContent;
	}

	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}

	public String getRspContent() {
		return rspContent;
	}

	public void setRspContent(String rspContent) {
		this.rspContent = rspContent;
	}
}