package org.ada.study.io.thread.forkjoin.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.ada.study.io.thread.forkjoin.base.IRegionWork;
import org.ada.study.io.thread.forkjoin.base.RegionBaseTask;

/**  
 * Filename: RegionTest.java  <br>
 *
 * Description:  测试两个线程同时执行   分区作业 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月4日 <br>
 *
 *  
 */

public class RegionTest extends RegionBaseTask<String>{
	
	private static final long	serialVersionUID	= 1L;

	/**
	 * 
	 * @param datas 分区前的所有数据
	 * @param name 主任务名称
	 * @param regionLength 分区中数据大小
	 */
	public RegionTest(List<String> datas, String name,Integer regionLength) {
		super( datas, name ,new SubTaskWork(),regionLength);
	}
	
	
	/**
	 * 子任务，具体作业 
	 */
	static class SubTaskWork implements IRegionWork<String>{
		@Override
		public void doWork(List<String> data) {
			for(String t:data){
				try {
					System.out.println(t);
					Thread.sleep( 1000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 测试案例
	 * @param args
	 * @author: CZD  
	 * @Createtime: 2017年7月4日
	 */
	public static void main(String[] args) {
		
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				List<String> funds = new ArrayList<String>();
				for (int i = 0; i < 10; i++) {
					funds.add( "main-1-fund-" + i );
				}
				
				Integer count = new ForkJoinPool().invoke(new Region2Test(funds,"thread-main-1",30));
				System.out.println("开启任务个数："+count);
			}
		} ).start();
		
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				List<String> funds2 = new ArrayList<String>();
				for (int i = 0; i < 20; i++) {
					funds2.add( "main-2-fund-" + i );
				}
				Integer count = new ForkJoinPool().invoke(new RegionTest(funds2,"thread-main-2",80));
				System.out.println("开启任务个数："+count);
			}
		} ).start();
		
		
	}

}
