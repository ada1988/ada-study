package org.ada.study.io.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**  
 * Filename: InterruptLockTest.java  <br>
 *
 * Description: reentrantLock 可中断  <br>
 * 
 * 仅一个线程能够执行完毕，另一个线程被中断。轻松解决死锁问题。
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月2日 <br>
 *
 * 1、如果将该方法中的lock(),统一换成tryLock() 将不存在死锁现象，但不能确保程序获取到锁并执行对应代码。
 * 2、ReentrantLock(true);获取公平锁，但性能方面有损失
 *  
 */

public class InterruptLockTest implements Runnable{
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	
	int lock;
	
	public InterruptLockTest(int lock){
		this.lock = lock;
	}
	public void run(){
		try{
			if(lock ==1){
				lock1.lockInterruptibly();//获取锁，优先处理中断事件
				try{
					Thread.sleep(2000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				lock2.lockInterruptibly();
			}else{
				lock2.lockInterruptibly();
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				lock1.lockInterruptibly();
			}
			
			System.out.println(Thread.currentThread().getId()+":执行完毕！");
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			if(lock1.isHeldByCurrentThread())
				lock1.unlock();
			if(lock2.isHeldByCurrentThread())
				lock2.unlock();
			
			System.out.println(Thread.currentThread().getId()+":退出");
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		InterruptLockTest test1 = new InterruptLockTest( 1 );
		InterruptLockTest test2 = new InterruptLockTest( 2 );
		Thread r1 = new Thread(test1);
		Thread r2 = new Thread(test2);
		
		r1.start();r2.start();
		Thread.sleep( 1000 );
		//中断其中一个线程
		r2.interrupt();
	}
}
