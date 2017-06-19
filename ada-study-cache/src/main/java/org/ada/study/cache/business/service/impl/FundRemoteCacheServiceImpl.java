package org.ada.study.cache.business.service.impl;

import java.util.List;

import org.ada.study.cache.business.entity.FundBean;
import org.ada.study.cache.business.service.IFundDbService;
import org.ada.study.cache.business.service.IFundRemoteRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
 * Filename: FundRemoteCacheServiceImpl.java  <br>
 *
 * Description:  分布式缓存，业务实现（redis缓存，2小时失效时间） <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *  
 */
@Component("fundRemoteCacheService")
public class FundRemoteCacheServiceImpl implements IFundRemoteRedisService{
	@Autowired
	private IFundDbService fundDbService;
	
	String lockName = "";
	String cacheName = "";
	
	public FundBean queryRemoteRedisDetailById(String id) {
		//redis 分布式 获取锁，获取db数据到redis
		return fundDbService.queryDetailById( id );
	}

	@Override
	public List<FundBean> queryRemoteRedisAllFund() {
		//redis 分布式 获取锁，获取db数据到redis
		return fundDbService.queryAllFund();
	}

}
