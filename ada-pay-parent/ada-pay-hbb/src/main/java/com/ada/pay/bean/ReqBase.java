package com.ada.pay.bean;

/**  
 * Filename: ReqBase.java  <br>
 *
 * Description:  请求 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime:  2020年12月02日 <br>
 *
 *  
 */

public class ReqBase<T>{
	private String merchantNo;//由E账户给签约商户分配
	private String reqTime;//yyyymmddhhmmss
	private String reqNo;//签约商户调用方生成唯一编号，推荐使用uuid去 掉-，用于超时情况查询使用，返回参数会直接返回
	private String version;//为了保证系统升级业务连续做新老系统兼容，可取值v1,v2,v3默认v1
	private String secret;//使用rsa加密后的aes秘钥
	private String plaintext;//参数明文
	private String data;//Aes加密后的数据
	private String tradeCode;//子系统编号2 业务接口编号4
	private T reqData;//请求实体
	/**  
	 * @Title:  getMerchantNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getMerchantNo() {
		return merchantNo;
	}
	/**  
	 * @Title:  setMerchantNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	/**  
	 * @Title:  getReqTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getReqTime() {
		return reqTime;
	}
	/**  
	 * @Title:  setReqTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
	/**  
	 * @Title:  getReqNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getReqNo() {
		return reqNo;
	}
	/**  
	 * @Title:  setReqNo <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	/**  
	 * @Title:  getVersion <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getVersion() {
		return version;
	}
	/**  
	 * @Title:  setVersion <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**  
	 * @Title:  getSecret <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getSecret() {
		return secret;
	}
	/**  
	 * @Title:  setSecret <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**  
	 * @Title:  getPlaintext <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getPlaintext() {
		return plaintext;
	}
	/**  
	 * @Title:  setPlaintext <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}
	/**  
	 * @Title:  getData <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getData() {
		return data;
	}
	/**  
	 * @Title:  setData <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**  
	 * @Title:  getReqData <BR>  
	 * @Description: please write your description <BR>  
	 * @return: T <BR>  
	 */
	public T getReqData() {
		return reqData;
	}
	/**  
	 * @Title:  setReqData <BR>  
	 * @Description: please write your description <BR>  
	 * @return: T <BR>  
	 */
	public void setReqData(T reqData) {
		this.reqData = reqData;
	}
	/**  
	 * @Title:  getTradeCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getTradeCode() {
		return tradeCode;
	}
	/**  
	 * @Title:  setTradeCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
}
