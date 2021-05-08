package com.ada.pay.bean;

/**   
 * 查询账户信息
 */
public class TC020108ReqData extends ReqBase{
	
	private String accountNo;    //账户号  N

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
}