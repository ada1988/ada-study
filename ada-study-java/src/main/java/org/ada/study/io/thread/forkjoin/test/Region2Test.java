package org.ada.study.io.thread.forkjoin.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.ada.study.io.thread.forkjoin.base.IRegionWork;
import org.ada.study.io.thread.forkjoin.base.RegionBaseTask;

/**  
 * Filename: RegionTest.java  <br>
 *
 * Description: 测试案例  串行  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月4日 <br>
 *
 *  
 */

public class Region2Test extends RegionBaseTask<String>{
	
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * 子任务，具体作业 
	 */
	static class SubTaskWork implements IRegionWork<String>{
		@Override
		public void doWork(List<String> data) {
			for(String t:data){
				try {
					System.out.println(t);
					Thread.sleep( 100 );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 测试案例  串行
	 * @param args
	 * @author: CZD  
	 * @Createtime: 2017年7月4日
	 */
	public static void main(String[] args) {
		List<String> funds = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			funds.add( "main-1-fund-" + i );
		}
		Region2Test task = new Region2Test();
		task.init( funds,"thread-main-1",new SubTaskWork(),3 );
		
		Integer count = new ForkJoinPool().invoke(task);
		System.out.println("开启任务个数："+count);
		
		List<String> funds2 = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			funds2.add( "main-2-fund-" + i );
		}
		
		RegionTest task2 = new RegionTest();
		task.init( funds2,"thread-main-2",new SubTaskWork(),3 );
		
		count = new ForkJoinPool().invoke(task2);
		System.out.println("开启任务个数："+count);
	}

}
