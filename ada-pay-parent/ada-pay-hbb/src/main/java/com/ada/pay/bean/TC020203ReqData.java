package com.ada.pay.bean;

/**   
 * 结算接口
 */
public class TC020203ReqData extends ReqBase<TC020203ReqData>{
	
	private String refNo;    //平台订单No  Y

	private Integer settlementMode;    //结算模式  Y（1:结算到绑定卡—默认 2:结算到指定卡）
	
	private String accountNo;    //账簿号  N

	private String bindNo;    //绑定银行卡No  Y

	private String name;    //持卡人姓名  Y

	private String idType;    //证件类型  Y

	private String idCard;    //身份证  Y

	private String bankCard;    //银行卡  Y

	private String telephone;    //银行卡绑定的手机号  Y

	private String bankCode;    //银行编码  Y

	private String clearingBankCode;    //清算行号  Y

	private String amount;    //结算金额  N

	private String remark;    //交易备注  Y

	private String settlePloy;    //清算类型  N（暂时只支持T0，T1）

	private String callbackUrl;    //回调地址  Y

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Integer getSettlementMode() {
		return settlementMode;
	}

	public void setSettlementMode(Integer settlementMode) {
		this.settlementMode = settlementMode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBindNo() {
		return bindNo;
	}

	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSettlePloy() {
		return settlePloy;
	}

	public void setSettlePloy(String settlePloy) {
		this.settlePloy = settlePloy;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
}