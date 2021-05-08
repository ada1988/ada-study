package com.ada.pay.bean;

/**   
 * 扫码支付查询接口
 */
public class TC020305ReqData extends ReqBase{
	
	private String orderNo;    //交易订单号  N

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}