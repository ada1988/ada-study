/**  
 * @Title:  	  TC020101RspData.java   <br>
 * @Package:  	  com.jiuku.modules.feign.bank.model   <br>
 * 
 * @Description:  TODO(用一句话描述该文件做什么) <br>
 *  
 * @author: 	  CZD     <br>
 * @version:	  V1.0  <br>
 * @createTime:   2021年1月4日 下午6:49:30   <br>
 */  
package com.ada.pay.bean;

/**   
 * @ClassName:  TC020101RspData   <br>
 *
 * @Description:获取短信验证码
 *
 * @author: CZD <br>
 * @version:V1.0  <br>
 * @createTime:   2021年1月4日 下午6:49:30    <br>
 */

public class TC020101RspData{
	private String bizFlag;//业务处理标志位	N
	private String bizMsg;//业务处理信息	Y
	private String orderNo;//交易订单号		N
	private String sequenceNo;//发送序号	Y
	/**  
	 * @Title:  getBizFlag <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getBizFlag() {
		return bizFlag;
	}
	/**  
	 * @Title:  setBizFlag <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setBizFlag(String bizFlag) {
		this.bizFlag = bizFlag;
	}
	/**  
	 * @Title:  getBizMsg <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getBizMsg() {
		return bizMsg;
	}
	/**  
	 * @Title:  setBizMsg <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setBizMsg(String bizMsg) {
		this.bizMsg = bizMsg;
	}
	/**  
	 * @Title:  getOrderNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**  
	 * @Title:  setOrderNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**  
	 * @Title:  getSequenceNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getSequenceNo() {
		return sequenceNo;
	}
	/**  
	 * @Title:  setSequenceNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	
}
