package org.ada.study.cache.common.cache.v3.dome;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	//故意暴露非线程安全集合
	public static Map<String,FundEntity> db = new ConcurrentHashMap<String, FundEntity>();
	{
		FundEntity fund = null;
		for(int i=0;i<9;i++){
			fund = new FundEntity();
			fund.setId( "00"+i );
			fund.setFundCode( "code-00"+i );
			fund.setFundName( "name-00"+i );
			db.put( fund.keyString(), fund );
		}
	}
	@Override
	public FundEntity queryDbData(FundParam param) {
		return db.get( param.getFundId() );
	}

}
