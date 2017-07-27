package org.ada.study.concurrent.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**  
 * Filename: ConcurrentLinkedQueueTest.java  <br>
 *
 * Description: 高并发，无锁\链表队列  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月25日 <br>
 *
 *  
 */

public class ConcurrentLinkedQueueTest<T>{
	private Object full=new Object();
	private Object empty=new Object();
	
	private List<T> queue = new ArrayList<T>();
	private int capacity = 4;
	
	public void printSize(){
		System.out.println("容器大小："+queue.size());
	}
	
	public void put(T e){
		if(queue.size()==capacity){
			try {
				System.out.println("等待存放数据...");
				synchronized (full) {
					full.wait();
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		synchronized (queue) {
			queue.add( e );
		}
		synchronized (empty) {
			empty.notify();
		}
	}
	
	public T take(){
		if(queue.size()==0){
			try {
				synchronized (empty) {
					System.out.println("等待取数据...");
					empty.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T t = null;
		synchronized (queue) {
			t = queue.get( 0 );
			Iterator<T> iterator = queue.iterator();
			iterator.next();
			iterator.remove();
		}
		synchronized (full) {
			full.notify();
		}
		
		return t;
	}
	
	public static void main(String[] args) {
		ConcurrentLinkedQueueTest<String> test = new ConcurrentLinkedQueueTest<String>();
		new Thread(){
			public void run(){
				for(int i=0;i<38;i++){
					test.put( "put-"+i );
					System.out.println("放："+"put-"+i);
					test.printSize();
					try {
						Thread.sleep( new Random().nextInt( 1*300 ) );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		new Thread(){
			public void run(){
				for(int i=0;i<35;i++){
					String t = test.take();
					System.out.println("取："+t);
					test.printSize();
					try {
						Thread.sleep( new Random().nextInt( 1*6000 ) );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
