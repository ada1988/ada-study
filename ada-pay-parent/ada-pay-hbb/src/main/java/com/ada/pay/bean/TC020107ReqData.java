package com.ada.pay.bean;

/**   
 * 重置交易密码
 */
public class TC020107ReqData extends ReqBase{
	
	private String accountNo;    //账户号  N
	
	private String bindNo;    //三方绑定编号  N
	
	private String newPassword;    //新交易密码  N
	
	private String smsCode;    //验证码  N

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
}