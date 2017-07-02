package org.ada.study.io.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**  
 * Filename: SemaphoreTest.java  <br>
 *
 * Description: 信号量  <br>
 * 
 * 多个线程协作，提供强大的控制方法
 * 
 * 并发控制，限制多少个请求同时进行
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月2日 <br>
 *
 *  
 */

public class SemaphoreTest implements Runnable{
	final Semaphore semaphore = new Semaphore( 5 );
	
	public void run(){
			try {
				semaphore.acquire();//申请许可
				Thread.sleep( 2000 );
				System.out.println(Thread.currentThread().getId()+":done!");
				semaphore.release();//释放许可
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool( 20 );
		final SemaphoreTest s = new SemaphoreTest();
		
		for(int i=0;i<20;i++){
			executor.submit( s );
		}
	}
}
