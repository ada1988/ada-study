package org.ada.study.io.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**  
 * Filename: ConditionTest.java  <br>
 *
 * Description:  重入锁的好搭档Condition <br>
 * 
 * 一把锁，多个条件
 * 串行：1、读时不能改。2、改时不能读
 * 优点：多个条件互相唤醒，简单实现生产者消费者问题。（参考ArrayBlockingQueue源码）
 * 
 * 
 * 该代码存在问题，无法按套路正常执行
 * 
 * 本打算：主线程通知 子线程执行
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月2日 <br>
 *
 *  
 */

public class ConditionTest implements Runnable{
	public static ReentrantLock lock = new ReentrantLock();
	
	public static Condition condition = lock.newCondition();
	
	public void run(){
		try{
			System.out.println("获取锁");
			lock.lock();
			condition.wait();//线程等待，释放lock锁
			System.out.println("Thread is going on");
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ConditionTest test = new ConditionTest();
		Thread t1 = new Thread(test);
		t1.start();
		Thread.currentThread().sleep( 5000 );
		lock.lock();
		condition.signal();
		lock.unlock();//必须释放，signal仅唤醒线程，但 不释放锁。
		System.out.println("结束");
	}
}
