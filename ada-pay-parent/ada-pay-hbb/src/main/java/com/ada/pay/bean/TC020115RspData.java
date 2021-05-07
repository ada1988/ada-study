package com.ada.pay.bean;

import java.util.List;
import java.util.Map;

/**   
 * 绑定卡查询
 */
public class TC020115RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private List<Map<String, Object>> bindBankInfos;

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

	public List<Map<String, Object>> getBindBankInfos() {
		return bindBankInfos;
	}

	public void setBindBankInfos(List<Map<String, Object>> bindBankInfos) {
		this.bindBankInfos = bindBankInfos;
	}
}