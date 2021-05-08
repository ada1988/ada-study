package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 个人网银支付
 */
public class TC020120ReqData extends ReqBase<TC020120ReqData>{
	
	private String accountNo;    //账户号  N
	
	private BigDecimal amount; //充值金额  N

	private String refNo; //原始业务编号  N
	
	private String callbackUrl; //回调地址  Y
	
	private String redirectUrl; //跳转地址  N
	
	private String remark; //交易备注  Y
	
	private String bankCard; //银行卡号  N

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

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
}