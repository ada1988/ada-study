package org.ada.study.cache.common.cache.v3.dome;

import org.ada.study.cache.common.cache.v3.IBaseKey;

/**  
 * Filename: FundParam.java  <br>
 *
 * Description:  查询实体 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月16日 <br>
 *
 *  
 */

public class FundParam implements IBaseKey{
	private String fundId;
	@Override
	public String keyString() {
		return this.fundId;
	}
	public String getFundId() {
		return this.getFundId();
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

}
