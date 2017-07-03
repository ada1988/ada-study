package org.ada.study.io.thread.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**  
 * Filename: DivTask.java  <br>
 *
 * Description: 测试：异常丢失  <br>
 * 
 * 本该打印5条信息，怎么丢了一条。
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月3日 <br>
 *
 *  
 */

public class DivTask implements Runnable{
	int a,b;
	
	public DivTask(int a,int b){
		this.a = a;
		this.b = b;
	}
	public void run(){
		double re = a/b;
		System.out.println(re);
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor pools = new ThreadPoolExecutor( 0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>() );
		for(int i=0;i<5;i++){
			pools.submit( new DivTask( 100, i ) );
			//下面两种解决方案，还有一种解决方案，TraceTreadPoolExxcutor
			//1、pools.execute( new DivTask( 100, i ) ); //打印错误信息
			/*2、Future re = pools.submit( new DivTask( 100, i ) );
			re.get();//打印错误信息*/
		}
	}
	
}
