package org.ada.study.tools.task.row;

import java.util.List;
import java.util.Map;

import org.ada.study.tools.task.common.AdaCacheConstant;
import org.ada.study.tools.task.common.BackThreadPool;
import org.ada.study.tools.task.common.IRegionCheckStrategy;
import org.ada.study.tools.task.common.ISchedulingCacheStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**  
 * Filename: BaseCacheMultThread.java  <br>
 *
 *	row 解读read or write 
 *
 * Description:    读写分离
 * APP详情，缓存方案：
 * 
 * 	原始方案：调用时对数据进行缓存；
 * 
 *  近期方案：项目启动时，延时5秒初始化[本次使用版本]的所有基金的分布式缓存，根据具体时间HH，获取对应分区中的数据（过期时间为11小时）。
 *  	   (已废弃)定时任务每隔40分钟，生成下一版本缓存数据（防止在临界点4、10、16、22点启动项目，导致下次尚未缓存数据，所以任务间隔时间为40分钟）。
 *  
 *  问题：
 *  	1、具体区间段，不可重复更新同一版本的缓存数据（幂等性判断）  已解决
 *  	2、定时任务40分钟未完成，会产生堆积	已解决
 *  		定时任务5小时执行一次，首次更新两个版本的缓存，其他情况任务更新下一版块缓存。(临界区：{4:30|10:30|16:30|16:30|22:30} 这些时间到准点的时间启动项目，可能出现一些跨区问题)
 *  
 *  具体时间点的数据分布：
 *  	缓存区域：
 *  		0区、1区、2区、3区
 *  	时间点对应的数据区域：
 * 			[0-5]点    	0区
 *  		[6-11]点  	1区
 *  		[12-17]点  	2区
 *  		[18-23]点  	3区
 *  
 *  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *  +++++++++++++++++思路
 *  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *  依据时间作为版本管理原因：不必刻意维护版本（集群问题、一致性问题）
 *  如果服务器时间存在太大误差，可能导致数据只有等待过期后，才能获取最新数据。
 *  
 *  DateUtil.specialUpdateHH()  获取下一版本
 *  DateUtil.specialQueryHH() 本次使用版本
 *  
 *  关键因素：
 *  	时间、分区、超时时间、定时任务
 *  
 * 区域： 	|_______0区_______|_______1区__________|_____2区_________|_______3区_______|
 * 时间： 	0  1  2  3  4  5  6  7  8  9  10  11 12 13 14 15 16 17 18 19 20 21 22 23 24
 * 
 * 
 * 定时任务：相当于等步长的向前存放数据，等待对应时间点的到达，用户获取数据；
 * 过期时间：相当于等步长的移除过期数据，为定时任务存放数据做清理空间工作；
 * 时间：相当于版本控制，某连续的时间段内对应某一区域；
 * 分区：4个区域，容易移动数据。单个区域替换数据（无法实现读写分离）
 *  
 *  
 *  11小时过期，保证所有区域数据，可以存活近乎2个周期；
 *  5个小时执行一次任务，保证下一区域数据被更新（可能某时间会出现未更新数据的情况）
 *  
 *  集群情况：多台服务器同时执行，基于isPass接口判断是否有服务正在执行，如果true，跳过整个某只基金。
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

public abstract class BaseCacheMultThread<T extends IBaseEntity> {
	private final Logger		logger	= LoggerFactory.getLogger( getClass() );
	
	/**
	 * 获取所有实现了定时刷新缓存业务的实现类
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月11日
	 */
	public abstract Map<String, ISchedulingCacheStrategy> cacheStrategys();
	
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
	public abstract List<T> allDatas();
	
	int threadSize = BackThreadPool.THREAD_SIZE;//线程个数
	
	private Boolean isFirst = true;
	
	int speed = 10;//平均速度10毫秒一条
	/**
	 * 三次重试，第一次：1分钟后调用；第二次：2分钟后调用；第三次：3分钟后调用
	 * 需要三次机制时，最大时间不确定，但最大时间不超过15分钟。（忽略接口中调用多个三方接口）
	 * 
	 * 如果第三方接口出问题，无法获取数据。
	 * 
	 * 
	 * 解决时间过长：多线程通知更新缓存：10个线程同时执行数据
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	public void doWork(){
		try {
			long startTime = System.currentTimeMillis();
			logger.error( "更新缓存--定时任务" + "每"+AdaCacheConstant.SCHEDULING_TIME+"毫秒执行一次");
			List<T> allDatas = null;
			try {
				allDatas = allDatas();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != allDatas && allDatas.size() > 0){
				//数据分区，丢给对应线程
				doRegion(allDatas);
			}else{
				for(int failNum = 0 ;failNum < 3;failNum++){
					Thread.currentThread().sleep( 60*1000*(failNum+1) );//三次重试，第一次：1分钟后调用；第二次：2分钟后调用；第三次：3分钟后调用
					logger.error( "更新缓存--定时任务--接口调用失败重试次数:" +(failNum+1));
					try {
						allDatas = allDatas();
						doRegion(allDatas);
						if (null != allDatas && allDatas.size() > 0) break;
						else logger.error( "更新缓存--定时任务--接口调用失败重试次数:" +(failNum+1)+"调用接口、更新缓存成功！");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			long endTime = System.currentTimeMillis();
			logger.error( endTime+"更新缓存--定时任务--APPFundDetailCacheMultThread--doWork() 总耗时:" +(endTime-startTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 多线程执行:数据分区，丢给对应线程
	 * @param funds
	 * @author: CZD  
	 * @Createtime: 2017年7月3日
	 */
	public void doRegion(List<T> allDatas){
		
		//数据分区，丢给对应线程
		int regionSize = allDatas.size()/threadSize;
		
		if(threadSize*regionSize<allDatas.size())
			regionSize = regionSize + 1;
		
		logger.error( "所有数据总条数："+allDatas.size()+" 分区个数："+threadSize+"  区域个数："+regionSize);
		
		for(int i=0;i<threadSize;i++){
			int fromIndex = i*regionSize;
			int toIndex = fromIndex + regionSize;
			if(threadSize == i+1){
				toIndex = allDatas.size()-1;
			}
			
			logger.error( i+"区的数据：["+fromIndex + "->"+toIndex+"]");
			List<T> regionFunds = allDatas.subList( i*regionSize, toIndex );
			BackThreadPool.fundCacheUpdateThreadPool.execute( new Thread(new UpdatePcDetailCache( regionFunds ,isFirst,i)) );
		}
		logger.error( "更新缓存--定时任务--调用接口、更新缓存成功！  ");
		isFirst = false;
	}
	
	/**
	 * 更新缓存
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	class UpdatePcDetailCache implements Runnable{
		
		private List<T> subDatas;
		private Boolean isFirst ;
		private int region;
		
		public UpdatePcDetailCache(List<T> subDatas,Boolean isFirst,int region){
			this.subDatas = subDatas;
			this.isFirst = isFirst;
			this.region = region;
		}
		long startTime = 0L;
		public void run(){
			if(!getRegionCheckStrategy().isPass( region )){//多台服务器可能同时执行该区域的数据，如果发现已有服务器执行，跳过整个区域
				try {
					ISchedulingCacheStrategy<Object,Object> st = null;
					for (T data : subDatas) {
						label:
							for(String key:cacheStrategys().keySet()){//多个接口，需要缓存，所以迭代接口
								st = cacheStrategys().get( key );
								/**
								 * 执行自定义装载的请求
								 */
								for(Object request:st.warpRequests( data.getId() ,isFirst)){//某个接口，可能需要多次请求，完成不同参数的缓存，所以迭代请求
									if(st.isPass( request )) {
										logger.error( "跳过---线程:{},类:{},编码:{},首次:{}",Thread.currentThread().getName(),key,data.getId(),isFirst);
										Thread.currentThread().sleep( speed );//产生死循环假象(休眠一段时间，跳出某支基金)
										break label;
									}
									startTime = System.currentTimeMillis();
									st.updateCache( request );
									logger.error( "线程:{},类:{},编码:{},耗时:{},首次:{}",Thread.currentThread().getName(),key,data.getId(),(System.currentTimeMillis()-startTime),isFirst);
								}
							}
							Thread.currentThread().sleep( speed );
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					getRegionCheckStrategy().removeDistributedKey( region );//销毁
				}
			}else{
				logger.error( "跳过分区---线程:{},分区:{},首次:{}",Thread.currentThread().getName(),region,isFirst);
			}
		}
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}
}
