package org.ada.study.cache.common.cache.v3.dome;

import org.ada.study.cache.common.cache.v3.IBaseKey;

/**  
 * Filename: FundCache.java  <br>
 *
 * Description:  缓存实体 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月16日 <br>
 *
 *  
 */

public class FundEntity implements IBaseKey{
	private String id;
	private String fundCode;
	private String fundName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	@Override
	public String keyString() {
		return this.getId();
	}
	@Override
	public String toString() {
		return "FundEntity [id=" + id + ", fundCode=" + fundCode + ", fundName=" + fundName + "]";
	}
	
}
