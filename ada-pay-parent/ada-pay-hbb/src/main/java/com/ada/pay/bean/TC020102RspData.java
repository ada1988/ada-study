package com.ada.pay.bean;

/**   
 * 个人开通钱包
 */
public class TC020102RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String accountNo; //账户号码	Y
	
	private String orderNo;   //交易订单号	Y
	
	private String bindNo;    //绑定No	Y
	
	private String customerNo;//用户编码	Y
	
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
	
	public String getBindNo() {
		return bindNo;
	}
	
	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}
	
	public String getCustomerNo() {
		return customerNo;
	}
	
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
}