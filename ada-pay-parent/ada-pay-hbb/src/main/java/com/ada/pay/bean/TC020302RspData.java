package com.ada.pay.bean;

/**   
 * 签署电子签章
 */
public class TC020302RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String signNo; //电子签章签署凭证	Y
	
	private String contractUrl; //电子合同地址	Y
	
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

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}
}