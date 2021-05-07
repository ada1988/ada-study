package com.ada.pay.bean;

/**   
 * 扫码支付
 */
public class TC020304RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String extData;  //扩展字段	Y
	
	private String qrUrl;  //二维码支付地址	Y
	
	private String orderNo;   //交易订单号	Y
	
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

	public String getExtData() {
		return extData;
	}

	public void setExtData(String extData) {
		this.extData = extData;
	}

	public String getQrUrl() {
		return qrUrl;
	}

	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
}