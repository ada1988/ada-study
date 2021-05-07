package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 钱包协议支付
 */
public class TC020125ReqData extends ReqBase<TC020125ReqData>{
	
	private String refNo;    //平台订单No  N
	
	private String payerAccountNo;  //付款人账户号  N
	
	private String payeeAcctountNo;  //收款人账簿号  N
	
	private String bindNo; //绑定no  N

	private BigDecimal amount; //充值金额  N

	private String tradePassword; //付款人钱包密码  N
	
	private String callbackUrl; //结果回调地址  N
	
	private String remark; //交易备注  Y
	
	private String cvv; //Cvv码  Y
	
	private String expire; //信用卡有效期  Y

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getPayerAccountNo() {
		return payerAccountNo;
	}

	public void setPayerAccountNo(String payerAccountNo) {
		this.payerAccountNo = payerAccountNo;
	}

	public String getPayeeAcctountNo() {
		return payeeAcctountNo;
	}

	public void setPayeeAcctountNo(String payeeAcctountNo) {
		this.payeeAcctountNo = payeeAcctountNo;
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

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}
}