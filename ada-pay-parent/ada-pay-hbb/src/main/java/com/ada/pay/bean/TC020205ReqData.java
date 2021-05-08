package com.ada.pay.bean;

import java.util.List;

/**   
 * 订单清分
 */
public class TC020205ReqData extends ReqBase{
	
	private String refNo;    //平台订单NO  N
	
	private String orderNo;    //要请分的订单  N（支持 个人网银支付(020120) 企业网银支付(020121) 钱包协议支付(020125) 协议支付(020126) 快捷支付(020201) 钱包快捷支付(020117) 钱包余额支付(020116)）
	
	private String payerAccountNo;    //付款人账号  N
	
	private List<TC020205ReqPayeeInfosData> payeeInfos;    //收款人信息集合  N
	
	private Integer clearingMode;    //分账模式  Y
	
	private String ruleNo;    //分账规则编号  Y

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayerAccountNo() {
		return payerAccountNo;
	}

	public void setPayerAccountNo(String payerAccountNo) {
		this.payerAccountNo = payerAccountNo;
	}

	public List<TC020205ReqPayeeInfosData> getPayeeInfos() {
		return payeeInfos;
	}

	public void setPayeeInfos(List<TC020205ReqPayeeInfosData> payeeInfos) {
		this.payeeInfos = payeeInfos;
	}

	public Integer getClearingMode() {
		return clearingMode;
	}

	public void setClearingMode(Integer clearingMode) {
		this.clearingMode = clearingMode;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}
}