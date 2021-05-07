package com.ada.pay.bean;

/**   
 * 解除钱包绑定的银行卡
 */
public class TC020106RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
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
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}