package com.ada.pay.bean;

/**   
 * 签署电子签章
 */
public class TC020302ReqData extends ReqBase<TC020302ReqData>{
	
	private String name;    //名字  N
	
	private String idCard;    //证件号  N
	
	private String phone;    //手机号  N
	
	private String contractType;    //合同类型  N（1 资金结算服务协议 2 扣款服务协议）
	
	private String openType;    //签署人类型  N（1 个人 2 企业）
	
	private String landLinePhone;    //企业联系电话  Y
	
	private String transactorName;    //经办人姓名  Y

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getLandLinePhone() {
		return landLinePhone;
	}

	public void setLandLinePhone(String landLinePhone) {
		this.landLinePhone = landLinePhone;
	}

	public String getTransactorName() {
		return transactorName;
	}

	public void setTransactorName(String transactorName) {
		this.transactorName = transactorName;
	}
}