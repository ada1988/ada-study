package org.ada.study.tools.task.raw;

import java.util.List;
import java.util.Map;

import org.ada.study.tools.task.common.AdaCacheConstant;
import org.ada.study.tools.task.common.BackThreadPool;
import org.ada.study.tools.task.common.IRegionCheckStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: BaseCacheMultThread.java  <br>
 *
 *	row 解读read or write 
 *
 * Description:    读写未分离
 * APP详情，缓存方案：
 * 
 * 	原始方案：调用时对数据进行缓存；
 * 
 *  近期方案：项目启动时，延时5秒初始化[本次使用版本]的所有基金的分布式缓存。
 *  
 *  <br>
 * 
 * 多线程并发执行
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月19日 <br>
 *
 *  
 */

public abstract class RBaseCacheMultThread<T extends IBaseEntity> {
	private final Logger		logger	= LoggerFactory.getLogger( getClass() );
	
	/**
	 * 获取所有实现了定时刷新缓存业务的实现类
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月11日
	 */
	public abstract Map<String, ISchedulingCacheReadAndWriteStrategy> cacheStrategys();
	
	/**
	 * 获取分区是否执行策略
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月11日
	 */
	public abstract IRegionCheckStrategy getRegionCheckStrategy();
	/**
	 * 获取所有数据
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月11日
	 */
	public abstract int dataCount();
	
	/**
	 * 分页获取数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月21日
	 */
	public abstract List<T> queryPage(int pageNo,int pageSize);
	
	int threadSize = BackThreadPool.THREAD_SIZE;//线程个数
	
	int speed = 10;//平均速度10毫秒一条
	/**
	 * 解决时间过长：多线程通知更新缓存：10个线程同时执行数据
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	public void doWork(){
		try {
			long startTime = System.currentTimeMillis();
			logger.error( "更新缓存--定时任务" + "每"+AdaCacheConstant.SCHEDULING_TIME+"毫秒执行一次");
			int count = dataCount();
			if(count > 0){
				//数据分区，丢给对应线程
				doRegion(count);
			}
			long endTime = System.currentTimeMillis();
			logger.error( endTime+"更新缓存--定时任务--RBaseCacheMultThread--doWork() 总耗时:" +(endTime-startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 多线程执行:数据分区，丢给对应线程
	 * @param funds
	 * @author: CZD  
	 * @Createtime: 2017年7月3日
	 */
	public void doRegion(Integer count){
		
		//数据分区，丢给对应线程
		int regionSize = count/threadSize;
		
		if(threadSize*regionSize<count)
			regionSize = regionSize + 1;
		
		logger.error( "所有数据总条数："+count+" 分区个数："+threadSize+"  区域个数："+regionSize);
		
		for(int i=0;i<threadSize;i++){
			int fromIndex = i*regionSize;
			int toIndex = fromIndex + regionSize;
			if(threadSize == i+1){
				toIndex = count-1;
			}
			logger.error( i+"区的数据：["+fromIndex + "->"+toIndex+"]");
			BackThreadPool.fundCacheUpdateThreadPool.execute( new Thread(new UpdatePcDetailCache( i , regionSize)) );
			/*try {
				Thread.currentThread().sleep( speed*1000 );//10秒钟，等待其他集群处理（如果有同一时间时间差的情况）
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		logger.error( "更新缓存--定时任务--调用接口、更新缓存成功！  ");
	}
	
	/**
	 * 更新缓存
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	class UpdatePcDetailCache implements Runnable{
		
		private int region;
		private int regionSize;
		
		public UpdatePcDetailCache(int region,int regionSize){
			this.region = region;
			this.regionSize = regionSize;
		}
		long startTime = 0L;
		public void run(){
			if(!getRegionCheckStrategy().isPass( region )){//多台服务器可能同时执行该区域的数据，如果发现已有服务器执行，跳过整个区域
				List<T> subDatas = queryPage( region+1 , regionSize );
				if(null!=subDatas && subDatas.size()>0){
					try {
						ISchedulingCacheReadAndWriteStrategy<Object,Object> st = null;
						for (T data : subDatas) {
								for(String key:cacheStrategys().keySet()){//多个接口，需要缓存，所以迭代接口
									st = cacheStrategys().get( key );
									/**
									 * 执行自定义装载的请求
									 */
									for(Object request:st.warpRequests( data.getId() )){//某个接口，可能需要多次请求，完成不同参数的缓存，所以迭代请求
										startTime = System.currentTimeMillis();
										st.updateCache( request );
										logger.error( "线程:{},类:{},编码:{},耗时:{}",Thread.currentThread().getName(),key,data.getId(),(System.currentTimeMillis()-startTime));
									}
								}
								Thread.currentThread().sleep( speed );
						}
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						getRegionCheckStrategy().removeDistributedKey( region );//销毁
						if(null!=subDatas){
							subDatas.clear();
							subDatas = null;
						}
					}
				}
			}else{
				logger.error( "跳过分区---线程:{},分区:{}",Thread.currentThread().getName(),region);
			}
		}
	}
}
