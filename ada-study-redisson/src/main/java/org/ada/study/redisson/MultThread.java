package org.ada.study.redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;

/**  
 * Filename: MultThread.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年4月26日 <br>
 *
 *  
 */

public class MultThread implements Runnable{
	private RLock lock;
	public MultThread(RLock lock){
		this.lock = lock;
	}
	@Override
	public void run() {
		for(int i=0;i<10000;i++){
			try {
				long start = System.currentTimeMillis();
				boolean res = lock.tryLock(20, 10, TimeUnit.SECONDS);
				App.num = App.num +1;
				System.out.println("Thread:"+Thread.currentThread().getId()+":"+res+":"+(System.currentTimeMillis()-start)+":"+App.num);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			
			try {
				Thread.sleep( 10L );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
