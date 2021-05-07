package com.ada.pay.bean;

/**
 * 钱包协议支付异步回调结果
 */
public class TC020125CallBackData {
	
	private String bizFlag;
	private String bizMsg;
	private String accountNo;
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
