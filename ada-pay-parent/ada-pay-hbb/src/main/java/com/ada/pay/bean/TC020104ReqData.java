package com.ada.pay.bean;

/**   
 * 个人钱包绑卡
 */
public class TC020104ReqData{
	
	private String phone;    //银行卡预留手机号  N
	
	private String accountNo;  //账户号码  N
	
	private Integer idType;  //证件类型  N
	
	private String idCard;     //证件号  N（推荐使用二代身份证，字母x不区分大小写）
	
	private String name;  //姓名  N
	
	private String bankCard; //银行卡  N

	private String bankCode; //银行编码  Y
	
	private String clearingBankCode; //开户行清算行号  Y

	private String openingBankName; //开户分支行  Y

	private String bankName; //银行名称  Y

	private String smsCode; //短信验证码  N

	private Integer seqenceNo; //短信验证码序号  Y

	private String tradePassword; //钱包密码  N

	private String validDate; //信用卡有效期  Y（绑定信用卡必填格式：Mmyy）
	
	private String cvv; //Cvv码  Y（绑定信用卡必填，见卡背）
	
	private String province; //开户省  Y
	
	private String city; //开户市  Y
	
	private String area; //开户区  Y
	
	private String cardType; //银行卡类型  Y（1：借记卡 默认 2：贷记卡 3：准贷记卡）

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public Integer getSeqenceNo() {
		return seqenceNo;
	}

	public void setSeqenceNo(Integer seqenceNo) {
		this.seqenceNo = seqenceNo;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}