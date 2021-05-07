package com.ada.pay.bean;

/**   
 * 处理集合信息
 */
public class TC020205RspOrderClearingInfosData{
	
	private String payeeAccountNo;   //收款人账号	Y
	
	private String remark;    //备注	Y
	
	private String clearingNo;  //清分编号	Y
	
	private String handleStatus;  //订单状态  N  EA000000 成功不用等待异步回调，EA000005 处理中需要等待异步回调，EA000006 失败 |
	
	private String errorMsg;  //错误信息描述  Y

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClearingNo() {
		return clearingNo;
	}

	public void setClearingNo(String clearingNo) {
		this.clearingNo = clearingNo;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}