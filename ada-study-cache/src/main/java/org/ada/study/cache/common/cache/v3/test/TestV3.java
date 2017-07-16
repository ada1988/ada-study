package org.ada.study.cache.common.cache.v3.test;

import org.ada.study.cache.common.cache.v3.dome.FundEntity;
import org.ada.study.cache.common.cache.v3.dome.FundParam;
import org.ada.study.cache.common.cache.v3.dome.QueryFund;

/**  
 * Filename: TestV3.java  <br>
 *
 * Description: 测试第三版缓存  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月16日 <br>
 *
 *  
 */

public class TestV3 {
	public static void main(String[] args) {
		QueryFund fundCache = new QueryFund();
		
		FundParam param = new FundParam();
		
		FundEntity fund = fundCache.cacheData( param );
		
		System.out.println(fund.toString());
	}
}
