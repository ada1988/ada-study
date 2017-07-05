package org.ada.study.io.thread.forkjoin.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;


/**  
 * Filename: RegionBaseTask.java  <br>
 *
 * Description: 任务分区 基类  <br>
 * ExcuteService解决的是并发问题，而ForkJoinPool解决的是并行问题
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月4日 <br>
 *
 *  
 */

public class RegionBaseTask<T> extends RecursiveTask<Integer> {
	private static final long	serialVersionUID	= 1L;
	//private static int	taskNum	= 0;//由于子任务的序号  ，不支持多线程同时调用,改为map存储
	private static Map<String,Integer> taskNums = new HashMap<String,Integer>();
	private int	regionLength = 100;//区域大小
	private String mainName;//任务名称
	private String subName;//任务名称
	List<T>		datas		= null;
	private IRegionWork<T> regionWork = null;//具体分区作业任务
	
	/**
	 * 外部构造主线程时使用
	 * @param datas 所有数据
	 * @param name	主任务名称
	 * @param regionWork 子线程具体作业的任务
	 * @param regionLength 为每个分区设定数据块的大小
	 */
	public RegionBaseTask(List<T> datas,String mainName,IRegionWork<T> regionWork,Integer regionLength) {
		this.datas = datas;
		this.mainName = mainName;
		this.regionWork = regionWork;
		if(!taskNums.containsKey( mainName ))//首次构造
			taskNums.put( mainName, 0 );
		this.regionLength = regionLength;
	}
	/**
	 * 进内部使用
	 * @param datas
	 * @param name
	 * @param regionWork
	 * @param regionLength
	 */
	@SuppressWarnings("unused")
	private RegionBaseTask(final List<T> datas,final String mainName,final String subName,final IRegionWork<T> regionWork,final Integer regionLength) {
		this.datas = datas;
		this.mainName = mainName;
		this.subName = subName;
		this.regionWork = regionWork;
		this.regionLength = regionLength;
	}
	/**
	 * 数据分区，创建子任务
	 * @param funds
	 * @author: CZD  
	 * @Createtime: 2017年7月4日
	 */
	public void dataRegion(List<T> datas,List<RegionBaseTask<T>> subTasks){
		/**
		 * 数据分区，创建子任务
		 */
		int start = taskNums.get( mainName )==0?0:regionLength;
		int end = datas.size();
		List<T> subDatas = datas.subList( start, end);
		if(null!=subDatas && subDatas.size()> regionLength){
				/**
				 * 为子任务拆分数据
				 */
				subTasks.add( new RegionBaseTask<T>( subDatas.subList( 0, regionLength ) ,this.mainName,this.mainName+"-task-"+taskNums.get( mainName ),this.regionWork,this.regionLength));
				taskNums.put( mainName, taskNums.get( mainName ) + 1 );//任务序号
				dataRegion(subDatas,subTasks);//递归：数据分区，分割子任务
		}else{
			subTasks.add( new RegionBaseTask<T>( subDatas.subList( 0, subDatas.size() ) ,this.mainName,this.mainName+"-task-"+taskNums.get( mainName ),this.regionWork,this.regionLength));
			taskNums.put( mainName, taskNums.get( mainName ) + 1 );//任务序号
		}
			
	}
	
	@Override
	protected Integer compute() {
		
		
		// 分割子线程
		List<RegionBaseTask<T>> subTasks = new ArrayList<RegionBaseTask<T>>();
		/**
		 * 首次执行，递归分区，分任务
		 */
		if(taskNums.get( mainName ) ==0)
			dataRegion( datas, subTasks );
		else
			this.getRegionWork().doWork( this.getDatas() );
		
		/**
		 * 开始调度
		 */
		for (RegionBaseTask<T> subTask : invokeAll( subTasks )) {
				subTask.join();//等待完成
				System.out.println("任务名称："+subTask.getSubName()+"  数据大小："+subTask.getDatas().size());
		}
		return taskNums.get( mainName );
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public IRegionWork<T> getRegionWork() {
		return regionWork;
	}
	public void setRegionWork(IRegionWork<T> regionWork) {
		this.regionWork = regionWork;
	}
	public String getMainName() {
		return mainName;
	}
	public void setMainName(String mainName) {
		this.mainName = mainName;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}

}
