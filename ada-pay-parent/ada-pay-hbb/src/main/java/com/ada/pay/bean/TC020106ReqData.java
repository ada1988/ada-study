package com.ada.pay.bean;

/**   
 * 解除钱包绑定的银行卡
 */
public class TC020106ReqData extends ReqBase{
	
	private String accountNo;  //账户号码  N
	
	private String bindNo;  //三方绑定编号  N
	
	private String tradePassword; //交易密码  N
	
	private String smsCode;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBindNo() {
		return bindNo;
	}

	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
}