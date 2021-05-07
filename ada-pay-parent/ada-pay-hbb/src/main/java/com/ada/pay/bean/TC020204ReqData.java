package com.ada.pay.bean;

/**   
 * 开通账簿
 */
public class TC020204ReqData extends ReqBase<TC020204ReqData>{
	
	private String phone;    //手机号  N
	
	private String platformCustomerNo;    //客户号  N
	
	private Integer idType;    //证件类型  N
	
	private String idNo;    //证件号  N
	
	private String name;    //姓名  N
	
	private String bankCard;    //银行卡  N
	
	private String bankCode;    //银行编码  Y
	
	private String clearingBankCode;    //开户行清算行号  Y
	
	private String bankName;    //银行名称  Y
	
	private String openingBankName;    //开户分支行  Y
	
	private String settlementAccountType;    //分账账户类型  N（1 ：个人 2 ： 企业 3：平台）
	
	private String cardType;    //银行卡类型  Y（1：借记卡 默认 2：贷记卡 3：准贷记卡，只支持 1 借记卡）
	
	private Integer proportion;    //清分比率  Y（1-100的整数）
	
	private String legalPersonName;    //法人姓名  Y
	
	private String legalPersonDocNo;    //法人身份证号  Y

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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getClearingBankCode() {
		return clearingBankCode;
	}

	public void setClearingBankCode(String clearingBankCode) {
		this.clearingBankCode = clearingBankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getOpeningBankName() {
		return openingBankName;
	}

	public void setOpeningBankName(String openingBankName) {
		this.openingBankName = openingBankName;
	}

	public String getSettlementAccountType() {
		return settlementAccountType;
	}

	public void setSettlementAccountType(String settlementAccountType) {
		this.settlementAccountType = settlementAccountType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getProportion() {
		return proportion;
	}

	public void setProportion(Integer proportion) {
		this.proportion = proportion;
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