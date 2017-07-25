package org.ada.study.io.thread.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Filename: ThreadTest.java <br>
 *
 * Description: <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年7月25日 <br>
 *
 * 
 */

public class ThreadTest {
	private Object	lock	= new Object();

	class Son implements Runnable {
		private String name;
		public Son(String name){
			this.name = name;
		}
		
		public void run() {

			synchronized (lock) {
				try {
					lock.wait(100);
					for (int i = 0; i < 10; i++) {
						System.out.println( "Son..."+name+"  " + i );
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (mainLock) {
				mainLock.notify();
			}
		}
	}

	static Object	mainLock	= new Object();

	public static void main(String[] args) {
		ThreadTest test = null;
		List<ThreadTest> list = null;

		for (int j = 0; j < 4; j++) {
			list = new ArrayList<ThreadTest>();
			Thread t = null;
			for (int i = 0; i < 3; i++) {
				test = new ThreadTest();
				System.out.println( "Parent..." + i );
				t = new Thread( test.new Son("p"+i) );
				t.start();
				list.add( test );
			}
			
			for (ThreadTest test1 : list) {
				synchronized (test1.getLock()) {
					test1.getLock().notifyAll();
				}
			}
			synchronized (mainLock) {
				try {
					mainLock.wait(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Object getLock() {
		return lock;
	}

	public void setLock(Object lock) {
		this.lock = lock;
	}

}
