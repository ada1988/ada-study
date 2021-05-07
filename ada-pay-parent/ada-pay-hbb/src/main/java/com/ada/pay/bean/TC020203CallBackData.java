package com.ada.pay.bean;

/**
 * 结算异步回调结果
 */
public class TC020203CallBackData {
	
	private String bizFlag;
	private String bizMsg;
	private String orderNo;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
