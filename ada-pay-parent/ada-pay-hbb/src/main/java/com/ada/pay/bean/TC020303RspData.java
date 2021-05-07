package com.ada.pay.bean;

import java.util.Date;

/**   
 * 资金交易状态查询
 */
public class TC020303RspData{
	
	private String bizFlag;   //业务处理标志位	N
	
	private String bizMsg;    //业务处理信息	Y
	
	private String orderNo; //交易订单号	Y
	
	/**
	 * | 订单类型                                                     | 订单状态 | 状态信息               |
| ------------------------------------------------------------ | -------- | ---------------------- |
| 020112,020113,020117,020120,020121,020201,020126,020124,020125 | 1        | 订单接收               |
|                                                              | 2        | 请求三方异常           |
|                                                              | 3        | 三方返回异常           |
|                                                              | 4        | 订单接收等待回调       |
|                                                              | 5        | 三方处理失败           |
|                                                              | 6        | 三方处理成功，记账开始 |
|                                                              | 7        | 记账失败               |
|                                                              | 8        | 订单记账完成           |
|                                                              | 9        | 订单回调完成           |
| 020114                                                       | 1        | 订单接收               |
|                                                              | 2        | 请求三方异常           |
|                                                              | 3        | 三方返回错误           |
|                                                              | 4        | 三方返回未明           |
|                                                              | 5        | 提现同步完             |
|                                                              | 9        | 回调处理成功           |
|                                                              | 10       | 失败                   |
|                                                              | 11       | 处理中                 |
|                                                              | 12       | 未知                   |
|                                                              | 15       | T1订单接受等待处理     |
| 020203                                                       | 2        | 初始化                 |
|                                                              | 4        | 记账失败               |
|                                                              | 6        | 支付失败               |
|                                                              | 8        | 支付处理中             |
|                                                              | 10       | 支付成功，等待回调     |
|                                                              | 12       | 支付失败，等待回调     |
|                                                              | 14       | 支付成功，回调失败     |
|                                                              | 15       | 支付失败，回调失败     |
|                                                              | 16       | 支付失败，回调成功     |
|                                                              | 18       | 未知                   |
|                                                              | 20       | 成功                   |
| 020127                                                       | 0        | 未记账                 |
|                                                              | 1        | 记账成功               |
|                                                              | 2        | 记账失败               |
| 020304                                                       | 1        | 订单接收               |
|                                                              | 2        | 支付同步返回状态非处理中           |
|                                                              | 3        | 支付返回参数错误           |
|                                                              | 4        | 订单接收等待回调       |
|                                                              | 5        | 三方返回结果为失败           |
|                                                              | 6        | 三方返回结果处理成功，记账开始 |
|                                                              | 7        | 记账失败               |
|                                                              | 8        | 订单记账完成           |
|                                                              | 9        | 订单回调完成           |
|                                                              | 10       | 三方返回结果为处理中  |
| offline                                                       | 1        | 未记账                 |
|                                                              | 2        | 成功记账               |
	 */
	private String orderStatus; //订单状态	Y
	
	private String statusMsg; //状态信息	Y
	
	private String orderErrorCode; //订单错误码	Y
	
	private String orderErrorMsg; //订单错误信息	Y
	
	private Date orderCreateTime; //订单创建时间（long）	Y
	
	private String orderRemark; //订单备注	Y
	
	private String handleStatus; //处理状态	Y(EA000000（成功）,EA000005（处理中）,EA000006（处理失败))
	
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getOrderErrorCode() {
		return orderErrorCode;
	}

	public void setOrderErrorCode(String orderErrorCode) {
		this.orderErrorCode = orderErrorCode;
	}

	public String getOrderErrorMsg() {
		return orderErrorMsg;
	}

	public void setOrderErrorMsg(String orderErrorMsg) {
		this.orderErrorMsg = orderErrorMsg;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
}