package com.ada.pay.bean;

/**   
 * 绑定卡查询
 */
public class TC020115ReqData extends ReqBase<TC020115ReqData>{
	
	private String accountNo;    //账户号  N

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
}