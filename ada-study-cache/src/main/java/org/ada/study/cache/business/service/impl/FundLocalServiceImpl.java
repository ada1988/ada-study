package org.ada.study.cache.business.service.impl;

import org.ada.study.cache.business.entity.FundBean;
import org.ada.study.cache.business.service.IFundLocalCacheService;
import org.ada.study.cache.business.service.IFundRemoteRedisService;
import org.ada.study.cache.common.constant.AdaCacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**  
 * Filename: FundLocalServiceImpl.java  <br>
 *
 * Description:  本地堆缓存，业务实现 （本地缓存，永不失效--项目启动，初始化本地缓存）<br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */
@Component("fundLocalCacheService")
public class FundLocalServiceImpl implements IFundLocalCacheService{
	@Autowired
	private IFundRemoteRedisService fundRemoteRedisService;
	
	@Cacheable(cacheNames=AdaCacheConstant.FUND_PC_DETAIL,key="'"+AdaCacheConstant.FUND_PC_DETAIL+"'+'queryDetailById.'+#id")
	public FundBean queryLocalDetailById(String id) {
		return fundRemoteRedisService.queryRemoteRedisDetailById( id );
	}
	/**
	 * 更新本地缓存
	 */
	@CachePut(cacheNames=AdaCacheConstant.FUND_PC_DETAIL,key="'"+AdaCacheConstant.FUND_PC_DETAIL+"'+'queryDetailById.'+#id")
	public FundBean updateLocalDetailById(String id) {
		return fundRemoteRedisService.queryRemoteRedisDetailById( id );
	}
}
