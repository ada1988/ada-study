package org.ada.study.io.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**  
 * Filename: JoinTest.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月27日 <br>
 *
 *  
 */

public class JoinTest implements Runnable{
	
	public void run(){
		try{
			System.out.println("son : "+Thread.currentThread().getId());
			Thread.sleep( 1000*20 );
			System.out.println("Thread is going on");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread son = new Thread(new JoinTest());
		son.start();
		System.out.println("parent.....start"+Thread.currentThread().getId());
		try {
			son.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("parent.....end"+Thread.currentThread().getId());
	}
}
