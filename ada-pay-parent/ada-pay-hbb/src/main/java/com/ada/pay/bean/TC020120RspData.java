package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 个人网银支付
 */
public class TC020120RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String accountNo; //账户号	Y
	
	private String bankUrl;   //网银支付地址	Y
	
	private String orderNo;   //交易订单号	Y
	
	private BigDecimal availableBalance ;   //可用余额	Y
	
	private BigDecimal frozenAmount;//冻结金额   Y
	
	private BigDecimal inTransitAmount;//在途资金    Y
	
	private String handleStatus;   //处理状态	Y（EA000000 支付成功, EA000005 支付处理中, EA000006 支付失败）

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

	public String getBankUrl() {
		return bankUrl;
	}

	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
}