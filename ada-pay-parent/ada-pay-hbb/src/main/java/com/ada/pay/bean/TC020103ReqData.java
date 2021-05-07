package com.ada.pay.bean;

/**   
 * 企业开通钱包
 */
public class TC020103ReqData extends ReqBase<TC020103ReqData>{
	
	private String phone;    //企业联系电话  N
	
	private String platformCustomerNo;  //客户号  N
	
	private Integer idType;  //证件类型  N
	
	private String idCard;     //证件号  N
	
	private String companyName;  //企业名称  N
	
	private String bankCard; //银行卡  N

	private String clearingBankCode; //清算行号  N

	private String openingBankName; //开户分支行  Y

	private String bankName; //银行名称  Y
	
	private String bankCode; //银行简码  N

	private String tradePassword; //钱包密码  N

	private String legalPersonName; //法人姓名  Y

	private String legalPersonDocNo; //法人身份证号  Y

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlatformCustomerNo() {
		return platformCustomerNo;
	}

	public void setPlatformCustomerNo(String platformCustomerNo) {
		this.platformCustomerNo = platformCustomerNo;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getClearingBankCode() {
		return clearingBankCode;
	}

	public void setClearingBankCode(String clearingBankCode) {
		this.clearingBankCode = clearingBankCode;
	}

	public String getOpeningBankName() {
		return openingBankName;
	}

	public void setOpeningBankName(String openingBankName) {
		this.openingBankName = openingBankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getLegalPersonDocNo() {
		return legalPersonDocNo;
	}

	public void setLegalPersonDocNo(String legalPersonDocNo) {
		this.legalPersonDocNo = legalPersonDocNo;
	}
}