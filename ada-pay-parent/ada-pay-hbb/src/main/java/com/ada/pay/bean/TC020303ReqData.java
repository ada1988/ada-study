package com.ada.pay.bean;

/**   
 * 资金交易状态查询
 */
public class TC020303ReqData extends ReqBase<TC020303ReqData>{
	
	private String orderNo;    //交易订单号  N
	
	/**
	 * 订单数字 | 类型         |
	| -------- | ------------ |
	| 020112   | 钱包快捷充值 |
	| 020113   | 钱包网银充值 |
	| 020114   | 钱包提现     |
	| 020117   | 钱包快捷支付 |
	| 020203   | 结算         |
	| 020126   | 协议支付     |
	| 020124   | 钱包协议充值 |
	| 020125   | 钱包协议支付 |
	| 020127   | 转账充值     |
	| 020120   | 个人网银支付 |
	| 020121   | 企业网银支付 |
	| 020201   | 快捷支付     |
	| 020304   | 扫码支付     |
	| offline  | 二代来账     |
	 */
	private String orderType;    //订单类型  N

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}