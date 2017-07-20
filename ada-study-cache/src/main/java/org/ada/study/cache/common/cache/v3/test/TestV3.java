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
		param.setFundId( "001" );
		/**
		 * 首先：从堆内缓存中获取数据。
		 * 其次：从分布式环境，获取数据，并更新堆内缓存。
		 * 最后：从DB获取数据，并更新分布式、堆内缓存。
		 */
		System.out.println("==================首次调用==================");
		FundEntity fund = fundCache.cacheData( param );
		System.out.println("结果："+fund.toString());
		System.out.println("==================再次调用==================");
		fund = fundCache.cacheData( param );
		System.out.println("结果："+fund.toString());
		
		
		System.out.println("==================再次调用==================");
		fund = fundCache.cacheData( param );
		System.out.println("结果："+fund.toString());
	}
}
