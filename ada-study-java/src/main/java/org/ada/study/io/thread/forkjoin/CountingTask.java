package org.ada.study.io.thread.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.ada.study.io.thread.forkjoin.test.RegionTest;

/**
 * Filename: CountingTask.java <br>
 *
 * Description:  任务分割：递归分割法 -- (主任务分成多任务)<br>
 * 
 * 原始程序
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年7月4日 <br>
 *
 * 
 */

public class CountingTask<T> extends RecursiveTask<Integer> {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static int	regionLength = 10;//区域大小
	private static int	taskNum	= 0;//统计序号
	private String name;//任务名称
	List<T>		datas		= null;

	public CountingTask(List<T> datas,String name) {
		this.datas = datas;
		this.name = name;
	}
	/**
	 * 测试案例
	 * @param args
	 * @author: CZD  
	 * @Createtime: 2017年7月4日
	 */
	public static void main(String[] args) {
		List<String> funds = new ArrayList<String>();
		for (int i = 0; i < 101; i++) {
			funds.add( "fund-" + i );
		}
		
		Integer count = new ForkJoinPool().invoke(new CountingTask<String>(funds,"task-" + 0));
		System.out.println("开启任务个数："+count);
	}
	/**
	 * 数据分区
	 * @param funds
	 * @author: CZD  
	 * @Createtime: 2017年7月4日
	 */
	public void dataRegion(List<T> datas,List<CountingTask> subTasks){
		/**
		 * 数据分区，创建子任务,如果数据剩余太多，进行分割子任务
		 */
		int start = taskNum==0?0:regionLength;
		int end = datas.size();
		List<T> subDatas = datas.subList( start, end);
		if(null!=subDatas && subDatas.size()> regionLength){
				/**
				 * 为子任务拆分数据
				 */
				subTasks.add( new CountingTask( subDatas.subList( 0, regionLength ) ,"task-" + taskNum) );
				taskNum = taskNum + 1;//任务序号
				dataRegion(subDatas,subTasks);
		}else{
			subTasks.add( new CountingTask( subDatas.subList( 0, subDatas.size() ) ,"task-" + taskNum) );
		}
			
	}
	
	@Override
	protected Integer compute() {
		// 分割子线程
		List<CountingTask> subTasks = new ArrayList<CountingTask>();
		
		/**
		 * 首次执行，递归分区，分任务
		 */
		if(taskNum ==0)
			dataRegion( datas, subTasks );
		else
			doWork( this.getDatas() );
		
		/**
		 * 开始调度
		 */
		for (CountingTask subTask : invokeAll( subTasks )) {
				subTask.join();//等待完成
				System.out.println("任务名称："+subTask.getName()+"  数据大小："+subTask.getDatas().size());
		}
		return taskNum;
	}

	/**
	 * 真实业务
	 * @param data
	 * @author: CZD  
	 * @Createtime: 2017年7月4日
	 */
	public void doWork(List<T> data) {
		for(T t:data){
			System.out.println(Thread.currentThread().getId()+":"+t);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
