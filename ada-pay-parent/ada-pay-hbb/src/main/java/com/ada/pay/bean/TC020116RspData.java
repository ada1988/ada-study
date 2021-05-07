package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 钱包余额支付
 */
public class TC020116RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String accountNo; //账户号	Y
	
	private BigDecimal availableBalance ;   //可用余额	Y
	
	private BigDecimal frozenAmount;//冻结金额   Y
	
	private BigDecimal inTransitAmount;//在途资金    Y
	
	private String orderId;   //交易订单号	Y

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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}