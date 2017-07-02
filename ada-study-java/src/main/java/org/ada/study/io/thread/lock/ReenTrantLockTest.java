package org.ada.study.io.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**  
 * Filename: RentankLockTest.java  <br>
 *
 * Description: 可重入锁  <br>
 * 
 * JDK5.0 的早期版本中，重入锁的性能远远高于synchronized，但从JDK6.0开始，
 * JDK在synchronized上做了大量的优化，使得两者的性能差距并不大。
 * 
 * 所谓重入锁：仅限于当前获取锁的线程可以重入
 * 
 * lock.tryLock(5,TimeUnit.SECONDS);//尝试获取一把锁，在规定时间
 * lock.tryLock();//尝试获取一把锁，如果没有返回false，否则true；不会出现死锁问题。不会引起线程等待
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月2日 <br>
 *
 *  
 */

public class ReenTrantLockTest implements Runnable{
	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;
	public void run(){
		for(int j =0 ;j<1000;j++){
			lock.lock();
			//lock.lock();  重新获取锁，就算重入，但必须释放多次
			try{
				i++;
			}finally{
				lock.unlock();
				//lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			ReenTrantLockTest test = new ReenTrantLockTest();
			Thread t1=  new Thread(test);
			Thread t2=new Thread(test);
			t1.start();t2.start();
			t1.join();t2.join();
			System.out.println(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
