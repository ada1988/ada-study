package com.ada.pay.bean;

/**   
 * 钱包协议支付
 */
public class TC020125RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String accountNo; //账户号	Y
	
	private String orderNo;   //交易订单号	Y
	
	private String mark;//返回业务类型	Y（1 需要签约接入方下一步操作应该是调用确认签约接口；2 充值提交成功）
	
	
	private String phone;   //联系电话	Y
	
	private String bankCard;   //银行卡号	Y
	
	private String name;   //姓名	Y
	
	private String idNo;   //证件号	Y

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
}