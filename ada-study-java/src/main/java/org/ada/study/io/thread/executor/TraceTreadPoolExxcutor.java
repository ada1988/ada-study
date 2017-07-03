package org.ada.study.io.thread.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**  
 * Filename: TraceTreadPoolExxcutor.java  <br>
 *
 * Description:  继承父类，重写提交方法，抓取线程报错并打印 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月3日 <br>
 *
 *  
 */

public class TraceTreadPoolExxcutor extends ThreadPoolExecutor{

	public TraceTreadPoolExxcutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super( corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue );
	}

	public void execute(Runnable task){
		super.execute( wrap(task,clientTrace(),Thread.currentThread().getName()) );
	}
	
	public Future<?> submit(Runnable task){
		return super.submit( wrap(task,clientTrace(),Thread.currentThread().getName()) );
	}
	
	
	private Exception clientTrace(){
		return new Exception("Client stack trace");
	}
	
	private Runnable wrap(final Runnable task,final Exception clientStack,String clientThreadName){
		return new Runnable() {
			
			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
}
