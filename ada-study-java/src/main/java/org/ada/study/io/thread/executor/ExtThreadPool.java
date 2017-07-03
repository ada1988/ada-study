package org.ada.study.io.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**  
 * Filename: ExtThreadPool.java  <br>
 *
 * Description:  拓展线程池，执行线程时，前后增强 ；拒绝策略自定义<br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月3日 <br>
 *
 *  
 */

public class ExtThreadPool {
	public static class MyTask implements Runnable{
		public String name;
		public MyTask(String name)
		{
			this.name = name;
		}
		public void run(){
			System.out.println("正在执行："+Thread.currentThread().getId()+",Task Name:"+name);
			try {
				Thread.sleep( 100 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService es = new ThreadPoolExecutor( 5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),Executors.defaultThreadFactory(),new RejectedExecutionHandler() {
			/**
			 * 拒绝策略自定义
			 */
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println(r.toString()+"is discard");
				
			}
		} ){
			
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				super.beforeExecute( t, r );
				System.out.println("准备执行："+((MyTask)r).name);
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute( r, t );
				System.out.println("执行完成："+((MyTask)r).name);
			}

			@Override
			protected void terminated() {
				super.terminated();
				System.out.println("线程池退出");
			}
			
		};
		
		for(int i=0;i<6;i++){
			es.execute( new MyTask( "name-"+i ) );
			Thread.sleep( 10 );
		}
		es.shutdown();
	}
}
