package org.ada.study.cache.business.service.impl;

import java.util.List;

import org.ada.study.cache.business.entity.FundBean;
import org.ada.study.cache.business.service.IFundDbService;
import org.ada.study.cache.business.service.IFundRemoteRedisService;
import org.ada.study.cache.common.constant.RedisPrefixContant;
import org.ada.study.cache.common.support.RedisCacheClient;
import org.redisson.RedissonClient;
import org.redisson.core.RLock;
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
	
	@Autowired
	private RedissonClient redisson;
	
	private int expire = 60*60*2;//缓存两小时
	@Autowired
	private RedisCacheClient redisCacheClient;
	
	/**
	 * 获取DB数据，并将数据存放到redis分布式缓存，设置缓存时间2小时
	 */
	public FundBean queryRemoteRedisDetailById(String id) {
		String cacheName = RedisPrefixContant.FUND_PC_DETAIL+id;
		String lockName = RedisPrefixContant.FUND_PC_DETAIL_LOCK+id;
		
		FundBean fund = null;
		try {
			fund = redisCacheClient.getValue( cacheName );
			if(null == fund){
				RLock lock = redisson.getLock( lockName );
				try {
					lock.lock();
					//二次重试（防止加锁时，上一个加锁程序已缓存基金数据）
					fund = redisCacheClient.getValue( cacheName );
					if(null == fund){
						fund = fundDbService.queryDetailById( id );
						redisCacheClient.setValue( cacheName, fund, expire );
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fund;
	}
	
	/**
	 * 获取DB数据，并将数据存放到redis分布式缓存，设置缓存时间2小时
	 */
	@Override
	public List<FundBean> queryRemoteRedisAllFund() {
		String cacheName = RedisPrefixContant.FUND_PC_LIST_LOCK;
		String lockName = RedisPrefixContant.FUND_PC_LIST;
		
		List<FundBean> funds = null;
		try {
			funds = redisCacheClient.getValue( cacheName );
			if(null == funds){
				RLock lock = redisson.getLock( lockName );
				try {
					lock.lock();
					//二次重试（防止加锁时，上一个加锁程序已缓存基金数据）
					funds = redisCacheClient.getValue( cacheName );
					if(null == funds){
						funds = fundDbService.queryAllFund();
						redisCacheClient.setValue( cacheName, funds, expire );
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funds;
	}

}
