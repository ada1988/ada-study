package com.ada.pay.bean;

/**   
 * 获取短信验证码
 */
public class TC020101ReqData extends ReqBase{
	
	private String mobilePhone;  //手机号  N
	
	private Integer expirationTime;//验证码有效时间  Y
	
	private Integer sequenceNo;//发送序号  Y
	
	private String message;//自定义短信内容  Y
	
	private String sign;//短信签名  Y
	
	private String type;  //短信内容类型  Y
	/**  
	 * @Title:  getMobilePhone <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**  
	 * @Title:  setMobilePhone <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**  
	 * @Title:  getExpirationTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getExpirationTime() {
		return expirationTime;
	}
	/**  
	 * @Title:  setExpirationTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setExpirationTime(Integer expirationTime) {
		this.expirationTime = expirationTime;
	}
	/**  
	 * @Title:  getSequenceNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getSequenceNo() {
		return sequenceNo;
	}
	/**  
	 * @Title:  setSequenceNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	/**  
	 * @Title:  getMessage <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getMessage() {
		return message;
	}
	/**  
	 * @Title:  setMessage <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**  
	 * @Title:  getSign <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getSign() {
		return sign;
	}
	/**  
	 * @Title:  setSign <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**  
	 * @Title:  getType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getType() {
		return type;
	}
	/**  
	 * @Title:  setType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setType(String type) {
		this.type = type;
	}
}
