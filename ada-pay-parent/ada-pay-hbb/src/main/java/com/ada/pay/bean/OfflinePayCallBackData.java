package com.ada.pay.bean;

import java.util.Date;

/**
 * 线下转账充值异步回调结果
 */
public class OfflinePayCallBackData {
	
	//付款人钱包账户
    private String accountNo;
    
    //交易时间
    private Date dealTime;
    
    //交易订单号
    private String orderNo;
    
    //充值金额
    private Double amount;
    
    //付款人充值银行卡号
    private String bankCard;
    
    //充值状态(1 未入账  2 已入账 3 入账失败)
    private Integer status;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}