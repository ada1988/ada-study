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

public class ThreadTest2 extends Thread {

	public void run() {
		for (int i = 0; i < 4; i++) {
			System.out.println( "Parent..." + i );
		}
	}

	class Son implements Runnable {
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println( "Son..." + i );
			}

		}
	}

	public static void main(String[] args) {
		ThreadTest2 test = null;
		for (int j = 0; j < 4; j++) {

			for (int i = 0; i < 3; i++) {
				try {
					test = new ThreadTest2();
					test.start();
					test.join();
					Thread t = new Thread( test.new Son() );
					t.start();
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

}
