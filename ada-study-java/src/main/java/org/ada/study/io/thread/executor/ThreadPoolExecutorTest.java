package org.ada.study.io.thread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**  
 * Filename: ThreadPoolExecutorTest.java  <br>
 *
 * Description:  线程池执行器 <br>
 * 
 * 线程池执行器初始化方式：
 * 
 * 1、通过{@link Executors}工厂，生产对应的线程池执行器（newFixedThreadPool-固定大小的线程池、newWorkStealingPool）
 * 
 * 
 * 使用：
 * 1、通{@link ExecutorService}执行器，执行对应业务。如：提交业务进行执行，该执行器会在初始化的对应线程池，创建、复用对应线程来执行具体业务。
 * 	   
 * 
 * 
 * 
 * 
 * 
 * 注意：
 * 		1、future.get(1, TimeUnit.SECONDS);必须添加超时时间，否则会拖垮程序。
 * 		2、 ExecutorService必须显示关闭  executor.shutdown();//显示关闭
 * 		   executor.awaitTermination(5, TimeUnit.SECONDS);//达到等待时间后关闭
 * 
 * 
 * 新认识：
 * 		1、ExecutorService解决的是并发问题，而ForkJoinPool解决的是并行问题
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月27日 <br>
 *
 *  
 */

public class ThreadPoolExecutorTest {
	public static void main(String[] args) throws Exception {
		new ThreadPoolExecutorTest().newWorkStealingPoolTest();
	}
	
	/**
	 * 并行 线程池 测试
	 * ExcuteService解决的是并发问题，ForkJoinPool解决的是并行问题
	 * 
	 * work-stealing模式：所有在池中的线程尝试去执行其他线程创建的子任务，
	 * 这样就很少有线程处于空闲状态，非常高效
	 * 
	 * @author: CZD  
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @Createtime: 2017年6月27日
	 */
	public void newWorkStealingPoolTest() throws InterruptedException, ExecutionException{
		//并发因子 parallelism
		ExecutorService executorService = Executors.newWorkStealingPool( 3 );
		//串行执行
		for(int i = 30;i>0;i--){
			Future<String> future = executorService.submit(new AdaCallable(""+i) );
			System.out.println(future.get());
		}
	}
	public void newWorkStealingPoolTest2() throws InterruptedException, ExecutionException{
		//并发因子 parallelism
		ExecutorService executorService = Executors.newWorkStealingPool( 3 );
		
		List<AdaCallable> list = new ArrayList<ThreadPoolExecutorTest.AdaCallable>();
		//串行执行
		for(int i = 30;i>0;i--){
			list.add(new AdaCallable(""+i) );
		}
		List<Future<String>> futures = executorService.invokeAll( list );
		for(Future<String> future:futures)
			System.out.println(future.get());
	}
	
	
	class AdaCallable implements Callable<String>{
		String global = "";
		String name;
		public AdaCallable(String name){
			this.name = name+":Id="+Thread.currentThread().getId();
			this.global = "[myself-"+name+"]";
		}
		@Override
		public String call() throws Exception {
			System.out.println("Thread:"+this.name+"  method....");
			return this.name+":"+this.global+":"+new Random().nextInt( 10 );
		}
	}
}
