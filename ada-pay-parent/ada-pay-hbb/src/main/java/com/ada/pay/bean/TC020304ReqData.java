package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 扫码支付
 */
public class TC020304ReqData extends ReqBase{
	
	private String accountNo;    //账户号  N
	
	private BigDecimal amount;    //支付金额  N
	
	private String productName;    //商品名称  N
	
	private String refNo;    //原始业务编号  N
	
	private String callbackUrl;    //回调地址  Y
	
	private String redirectUrl;    //跳转地址  Y
	
	private String remark;    //交易备注  Y
	
	private String name;    //支付人姓名  Y
	
	private String phone;    //手机号  Y
	
	private String memo;    //备注信息  Y
	
	private String subMerchantClearType;    //二级商户清算  Y
	
	private String extData;    //扩展字段  Y

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSubMerchantClearType() {
		return subMerchantClearType;
	}

	public void setSubMerchantClearType(String subMerchantClearType) {
		this.subMerchantClearType = subMerchantClearType;
	}

	public String getExtData() {
		return extData;
	}

	public void setExtData(String extData) {
		this.extData = extData;
	}
}