package org.ada.study.tools.task.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**  
 * Filename: BackThreadPool.java  <br>
 *
 * Description:  后端进程的线程池 <br>
 * 
 * jdk6 ExecutorService 实现线程池
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月3日 <br>
 *
 *  
 */

public class BackThreadPool {
	
	public static final String NAME = "product-fund";//线程前缀
	
	public static final int THREAD_SIZE = 4;
	
	public static ExecutorService fundCacheUpdateThreadPool = Executors.newFixedThreadPool( THREAD_SIZE, new NamedThreadFactory( NAME ) );

}
