package com.ada.pay.bean;

import java.math.BigDecimal;

/**   
 * 收款人信息
 */
public class TC020205ReqPayeeInfosData{
	
	private String payeeAccountNo;    //收款人账号  N
	
	private BigDecimal amount;    //分账金额  N
	
	private String remark;    //备注  Y

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}