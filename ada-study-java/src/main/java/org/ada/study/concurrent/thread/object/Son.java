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

public class Son implements Runnable{
	
	private static Object lock = new Object();//可以是this或者lock
	
	private String name;
	public Son(String name){
		this.name = name;
	}
	
	public void print(){
		for(int i=0;i<10;i++){
			System.out.println(name+"..son.."+i);
		}
	}
	
	public void run(){
		//该对象锁可以保证子线程的顺序执行
		synchronized (lock) {
			print();
		}
	}
}
