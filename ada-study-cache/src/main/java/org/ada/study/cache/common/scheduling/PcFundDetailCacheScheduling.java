package org.ada.study.cache.common.scheduling;

import java.util.List;

import org.ada.study.cache.business.entity.FundBean;
import org.ada.study.cache.business.service.IFundDbService;
import org.ada.study.cache.business.service.IFundLocalCacheService;
import org.ada.study.cache.business.service.IFundRemoteRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**  
 * Filename: PcFundDetailCacheScheduling.java  <br>
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月19日 <br>
 *
 *  
 */

public class PcFundDetailCacheScheduling {
	private final Logger		logger	= LoggerFactory.getLogger( getClass() );
	
	long limitDebug = -1;
	
	
	@Autowired
	private IFundRemoteRedisService fundRemoteRedisService;
	
	
	@Autowired
	private IFundLocalCacheService fundLocalCacheService;
	
	public void doWork(){
		try {
			long startTime = System.currentTimeMillis();
			logger.error( "PC端基金缓存--定时任务" + "每2小时执行一次");
			List<FundBean> funds = null;
			try {
				funds = fundRemoteRedisService.queryRemoteRedisAllFund();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != funds && funds.size() > 0){
				updatePcDetailCache( funds );
				logger.error( "PC端基金缓存--定时任务--调用接口、更新缓存成功！");
			}else{
				for(int failNum = 0 ;failNum < 3;failNum++){
					Thread.currentThread().sleep( 60*(failNum+1)*1000 );//三次重试，第一次：1分钟后调用；第二次：2分钟后调用；第三次：3分钟后调用
					logger.error( "PC端基金缓存--定时任务--接口调用失败重试次数:" +(failNum+1));
					try {
						funds = fundRemoteRedisService.queryRemoteRedisAllFund();
						updatePcDetailCache( funds );
						if (null != funds && funds.size() > 0) break;
						else logger.error( "PC端基金缓存--定时任务--接口调用失败重试次数:" +(failNum+1)+"调用接口、更新缓存成功！");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			long endTime = System.currentTimeMillis();
			logger.error( endTime+"PC端基金缓存--定时任务--PcFundDetailCacheScheduling--doWork() 总耗时:" +(endTime-startTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新缓存
	 * 速度：平均速度10毫秒一条（不考虑第三方接口情况）
	 * 四千条数据: 4000*10 /1000 = 40秒结束
	 * @param funds
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	public void updatePcDetailCache(List<FundBean> funds){
		try {
			long startTime = 0L;
			long endTime = 0L;
			int i = 0;
			for (FundBean fund : funds) {
				startTime = System.currentTimeMillis();
				fundLocalCacheService.updateLocalDetailById( fund.getId() );
				endTime = System.currentTimeMillis();
				logger.error( "PC端基金缓存--定时任务--updateCacheGraphsData 耗时:"+fund.getId()+"---" +(endTime-startTime));
				Thread.currentThread().sleep( 10 );
				//测试使用
				if(limitDebug>0)
				{
					i++;
					if(i>8) break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
