package org.ada.study.io.thread.lock;

import java.util.concurrent.CyclicBarrier;

/**  
 * Filename: CyclicBarrierTest.java  <br>
 *
 * Description: 循环栅栏  <br>
 * 
 * 可重复使用
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月2日 <br>
 *
 *  
 */

public class CyclicBarrierTest implements Runnable{
	static final CyclicBarrier cyclicBarrier = new CyclicBarrier( 2 );

	@Override
	public void run() {
		
	}
	
	
	
}
