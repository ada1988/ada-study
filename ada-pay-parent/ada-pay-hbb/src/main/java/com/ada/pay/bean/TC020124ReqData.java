package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 钱包协议充值
 */
public class TC020124ReqData extends ReqBase<TC020124ReqData>{
	
	private String accountNo;    //账户号  N
	
	private String bindNo;  //绑定no  N
	
	private BigDecimal amount;  //充值金额  N（个人最大充值50000）
	
	private String tradePassword; //付款人钱包密码  N

	private String refNo; //原始业务编号  N

	private String callbackUrl; //回调地址  Y
	
	private String remark; //交易备注  Y

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}