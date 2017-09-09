package org.ada.study.concurrent.thread.object;
/**  
 * Filename: Son.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月26日 <br>
 *
 *  
 */

public class Parent implements Runnable{
	
	private static Object lock = new Object();//可以是this或者lock
	
	private String name;
	public Parent(String name){
		this.name = name;
	}
	
	public void print(){
		for(int i=0;i<20;i++){
			System.out.println(name+"..parent.."+i);
		}
	}
	
	public void run(){
		//该对象锁可以保证Parent线程的顺序打印
		synchronized (lock) {
			print();
		}
		Thread t = new Thread(new Son( "" ));
		try {
			t.start();
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
