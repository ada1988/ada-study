package org.ada.study.cache.business.service.impl;

import org.ada.study.cache.business.service.IFundService;
import org.ada.study.cache.common.constant.AdaCacheConstant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**  
 * Filename: FundServiceImpl.java  <br>
 *
 * Description:  业务实现 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */
@Component("fundService")
public class FundServiceImpl implements IFundService{
	@Override
	@Cacheable(cacheNames=AdaCacheConstant.FUND_PC_DETAIL,key="#id")
	public String queryDetailById(String id) {
		return "IFundService  DB 数据!";
	}

}
