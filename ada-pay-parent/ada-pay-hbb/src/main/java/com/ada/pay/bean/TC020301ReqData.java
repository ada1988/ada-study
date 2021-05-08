package com.ada.pay.bean;

/**   
 * 处理日志查询
 */
public class TC020301ReqData extends ReqBase{
	
	private Integer reqDate;    //请求日期  N（格式:yyyymmdd）
	
	private String sourceReqNo;    //原始请求日志号  N

	public Integer getReqDate() {
		return reqDate;
	}

	public void setReqDate(Integer reqDate) {
		this.reqDate = reqDate;
	}

	public String getSourceReqNo() {
		return sourceReqNo;
	}

	public void setSourceReqNo(String sourceReqNo) {
		this.sourceReqNo = sourceReqNo;
	}
}