package com.ada.pay.bean;

/**   
 * 个人钱包绑卡
 */
public class TC020104RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String bindNo;    //绑定No	Y

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
	
	public String getBindNo() {
		return bindNo;
	}
	
	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}
}