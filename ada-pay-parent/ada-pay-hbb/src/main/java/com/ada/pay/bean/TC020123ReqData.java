package com.ada.pay.bean;

/**   
 * 签约确认
 */
public class TC020123ReqData extends ReqBase{
	
	private String accountNo;    //账户号  N
	
	private String smsCode;  //签约短信验证码  N
	
	private String orderNo;  //交易订单号  N

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}