package org.ada.study.cache.common.cache.v3.dome;

import org.ada.study.cache.common.cache.v3.AdaMissiongCahceBase;

/**  
 * Filename: QueryFund.java  <br>
 *
 * Description: 基金查询  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月16日 <br>
 *
 *  
 */

public class QueryFund extends AdaMissiongCahceBase<FundEntity, FundParam>{

	@Override
	public FundEntity queryDbData(FundParam param) {
		FundEntity fund = new FundEntity();
		fund.setId( "001" );
		fund.setFundCode( "code-001" );
		fund.setFundName( "name-001" );
		return fund;
	}

}
