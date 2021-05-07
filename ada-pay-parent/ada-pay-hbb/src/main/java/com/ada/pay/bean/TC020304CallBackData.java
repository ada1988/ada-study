package com.ada.pay.bean;

/**
 * 扫码支付异步回调
 */
public class TC020304CallBackData {
	
	private String bizFlag;
	private String bizMsg;
	private String accountNo;
	private String orderNo;
	private String handleStatus;
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
	public String getHandleStatus() {
		return handleStatus;
	}
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	
}
