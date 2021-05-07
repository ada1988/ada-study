package com.ada.pay.bean;

/**   
 * 重置交易密码
 */
public class TC020107RspData{
	
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