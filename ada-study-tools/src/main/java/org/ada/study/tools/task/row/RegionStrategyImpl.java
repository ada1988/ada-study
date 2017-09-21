package org.ada.study.tools.task.row;

import org.ada.study.tools.task.common.AdaCacheConstant;
import org.ada.study.tools.task.common.IRedisCacheTemplate;
import org.ada.study.tools.task.common.IRegionCheckStrategy;
import org.redisson.Redisson;
import org.redisson.core.RLock;

/**  
 * Filename: RegionStrategyImpl.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月21日 <br>
 *
 *  
 */

public class RegionStrategyImpl implements IRegionCheckStrategy{
	
	public RegionStrategyImpl(Redisson redisson,IRedisCacheTemplate redisCacheClient){
		this.redisson = redisson;
		this.redisCacheClient = redisCacheClient;
	}

	private final String BASE_LOCK = "spring.cloud.product.fund.";
	
	private final String BASE_REGION_NAME = BASE_LOCK +"region.";
	private final String BASE_REGION_LOCK = BASE_REGION_NAME +"lock.";
	
	private Redisson redisson;
	private IRedisCacheTemplate redisCacheClient;
	
	@Override
	public Boolean isPass(Integer region) {
		String keyStartName = BASE_REGION_NAME + region+".start";
		String keyEndName = BASE_REGION_NAME + region+".finish";
		String lockName = BASE_REGION_LOCK+region;
		
		RLock lock = redisson.getLock( lockName );
		try {
			lock.lock();
			/*
			 * 跳过情况：
			 * 		有服务正在执行；
			 * 		该区域数据已经执行完成；
			 */
			if(redisCacheClient.exists( keyEndName ) || redisCacheClient.exists( keyStartName ))
			{
				return true;
			}else{
				redisCacheClient.setValue( keyStartName, "0" ,2*60*60);//执行时间，最长为1小时(如果服务挂掉，一个小时后自动销毁，其他机器会继续接力)
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return false;
	}

	@Override
	public void removeDistributedKey(Integer region) {
		String keyStartName = BASE_REGION_NAME + region+".start";
		String keyEndName = BASE_REGION_NAME + region+".finish";
		String lockName = BASE_REGION_LOCK+region;
		
		RLock lock = redisson.getLock( lockName );
		try {
			lock.lock();
			if(redisCacheClient.exists( keyStartName ))
			{
				redisCacheClient.deleteValue( keyStartName );
				redisCacheClient.setValue( keyEndName, "0" ,AdaCacheConstant.TIME_OUT_FINISH.intValue());//该区域数据执行完毕(与过期时间相同)
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

}
