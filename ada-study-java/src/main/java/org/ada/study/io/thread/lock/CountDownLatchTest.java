package org.ada.study.io.thread.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**  
 * Filename: CountDownLatch.java  <br>
 *
 * Description: 倒计数器 不可重复使用  <br>
 * 
 * 程序未执行成功
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月2日 <br>
 *
 *  
 */

public class CountDownLatchTest implements Runnable{
	static final CountDownLatch end = new CountDownLatch( 10 );
	
	static final CountDownLatchTest d = new CountDownLatchTest();
	
	public void run(){
		try {
			Thread.sleep( new Random().nextInt(10)*1000 );
			System.out.println("检查中");
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool( 10 );
		for(int i=0;i<10;i++)
			exec.submit( d );
		//等待检查   
		end.wait();//等待countdownLatch中的数值变为0
		//统一执行
		System.out.println("执行");
		exec.shutdown();//关闭线程池
	}
}
