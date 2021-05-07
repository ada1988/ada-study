package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 企业网银支付
 */
public class TC020121ReqData extends ReqBase<TC020121ReqData>{
	
	private String accountNo;    //账户号  N
	
	private BigDecimal amount; //支付金额  N

	private String bankCode; //银行编码  N
	
	private String refNo; //原始业务编号  N
	
	private String callbackUrl; //回调地址  Y
	
	private String redirectUrl; //跳转地址  N
	
	private String remark; //交易备注  Y
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}