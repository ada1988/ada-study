package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 钱包余额支付
 */
public class TC020116ReqData extends ReqBase<TC020116ReqData>{
	
	private String payerAccountNo;    //付款钱包号  N
	
	private String payeeAccountNo;  //收款账号  N
	
	private String tradePassword; //钱包密码  Y
	
	private BigDecimal amount; //支付金额  N
	
	private String refNo; //原始业务订单  N
	
	private String remark; //交易备注  Y

	public String getPayerAccountNo() {
		return payerAccountNo;
	}

	public void setPayerAccountNo(String payerAccountNo) {
		this.payerAccountNo = payerAccountNo;
	}

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
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
}