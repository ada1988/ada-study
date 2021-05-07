package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 钱包提现
 */
public class TC020114ReqData extends ReqBase<TC020114ReqData>{
	
	private String accountNo;    //账户号  N
	
	private String tradePassword; //钱包密码  Y
	
	private BigDecimal amount; //提现金额  N
	
	private String bindNo; //绑定银行卡No  N
	
	private String callbackUrl; //回调地址  Y

	private String refNo; //原始业务订单  N
	
	private String remark; //交易备注  Y
	
	private String settlePloy; //结算标志  N
	
	private String smsCode; //短信验证码  Y

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBindNo() {
		return bindNo;
	}

	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSettlePloy() {
		return settlePloy;
	}

	public void setSettlePloy(String settlePloy) {
		this.settlePloy = settlePloy;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
}