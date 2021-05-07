package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 查询账户信息
 */
public class TC020108RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String accountNo; //账户号	Y
	
	private String accountType; //钱包类型	Y（100001个人钱包 100002企业钱包 101001企业分账账号 101002 个人分账账号 104001 平台分账账号）
	
	private String customerNo;   //用户号	Y
	
	private BigDecimal availableBalance ;   //可用余额	Y
	
	private BigDecimal frozenAmount;//冻结金额   Y
	
	private BigDecimal inTransitAmount;//在途资金    Y
	
	private Integer acountStatus;   //钱包状态	Y（1:正常 2:待审核 3：冻结）
	
	private String name;   //姓名	Y
	
	private String phone;   //电话	Y
	
	private String idType;   //证件类型	Y
	
	private String idNo;   //证件号	Y
	
	private String bindNos;   //绑卡No列 表	Y（使用逗号分隔的bindNo数组 例如111,222,333）
	
	private String orderNo;   //交易订单号	Y

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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
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

	public Integer getAcountStatus() {
		return acountStatus;
	}

	public void setAcountStatus(Integer acountStatus) {
		this.acountStatus = acountStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getBindNos() {
		return bindNos;
	}

	public void setBindNos(String bindNos) {
		this.bindNos = bindNos;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}