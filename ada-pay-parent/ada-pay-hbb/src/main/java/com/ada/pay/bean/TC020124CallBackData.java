package com.ada.pay.bean;

import java.math.BigDecimal;

/**
 * 钱包协议充值异步回调结果
 */
public class TC020124CallBackData {
	
	private String bizFlag;
	private String bizMsg;
	private String accountNo;
	private BigDecimal availableBalance;
	private BigDecimal frozenAmount;
	private BigDecimal inTransitAmount;
	private String orderNo;
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
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	public BigDecimal getInTransitAmount() {
		return inTransitAmount;
	}
	public void setInTransitAmount(BigDecimal inTransitAmount) {
		this.inTransitAmount = inTransitAmount;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
