package org.ada.study.concurrent.thread.interrupt;

/**
 * Filename: ThreadInterruptTest.java <br>
 *
 * Description: <br>
 * 
 * Thread.interrupt()方法和InterruptedException异常的关系？
 * 是由interrupt触发产生了InterruptedException异常？
 * 
 * public void interrupt() : 执行线程interrupt事件 public boolean isInterrupted() :
 * 检查当前线程是否处于interrupt public static boolean interrupted() ：
 * check当前线程是否处于interrupt，并重置interrupt信息。类似于resetAndGet()
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年7月27日 <br>
 *
 * 
 */

public class ThreadInterruptTest {
	private Object	lock	= new Object();	// 类中的属性对象 锁

	/**
	 * 持有对象锁，不进行释放
	 * 
	 * @author: CZD
	 * @Createtime: 2017年7月27日
	 */
	public void selfWaitObject() {
			String id = "ThreadId：" + Thread.currentThread().getId();
			System.out.println( id );
			synchronized (lock) {
				try {
					System.out.println( id + "开始等待...." );
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}

	/**
	 * 中断的方式，释放对象锁
	 * 
	 * @author: CZD
	 * @Createtime: 2017年7月27日
	 */
	public void selfTouchInterrupt(Thread thread) {
		System.out.println( "中断 对象锁 的 线程,休息一段时间：" + Thread.currentThread().getId() );
		try {
			Thread.currentThread().sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();// 中断线程
		try {
			Thread.currentThread().sleep( 2000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (thread.isInterrupted())// 是否被中断
		{
			System.out.println( thread.getId() + "被中断处理...." );
		}
	}

	public static void main(String[] args) {
		// 线程共享的资源
		ThreadInterruptTest test = new ThreadInterruptTest();
		// 守护 对象锁 的 线程
		Thread holdThread = new Thread() {
			public void run() {
				System.out.println( "守护 对象锁 的 线程：" + Thread.currentThread().getId() );
				test.selfWaitObject();// 锁住对象，并检查是否被中断
			}
		};
		holdThread.start();
		// 中断 对象锁 的 线程
		Thread tt = new Thread() {
			public void run() {
				System.out.println( "中断 对象锁 的 线程：" + Thread.currentThread().getId() );
				test.selfTouchInterrupt( holdThread );// 中断线程，释放锁
			}
		};
		tt.start();
		
		try {
			tt.join();//主线程等待，tt执行完毕
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
