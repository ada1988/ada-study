package org.ada.study.concurrent.thread.object;
/**  
 * Filename: ThreadObjectLockTest.java  <br>
 *
 * Description: 如何保证打印顺序，并要求父类首先执行，子类再执行  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月26日 <br>
 *
 *  
 */

public class ThreadObjectLockTest {
	public static void main(String[] args) {
		try {
			Thread t = null;
			for(int i=0;i<3;i++){
				t = new Thread(new Parent( "p"+i ));
				t.start();
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
