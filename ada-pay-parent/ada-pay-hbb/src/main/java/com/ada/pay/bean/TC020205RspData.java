package com.ada.pay.bean;

import java.util.List;

/**   
 * 订单清分
 */
public class TC020205RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String batchNo;  //批次号	Y
	
	private String orderNo;   //清分订单号	Y
	
	private List<TC020205RspOrderClearingInfosData> orderClearingInfos;   //处理集合  Y

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

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<TC020205RspOrderClearingInfosData> getOrderClearingInfos() {
		return orderClearingInfos;
	}

	public void setOrderClearingInfos(List<TC020205RspOrderClearingInfosData> orderClearingInfos) {
		this.orderClearingInfos = orderClearingInfos;
	}
}