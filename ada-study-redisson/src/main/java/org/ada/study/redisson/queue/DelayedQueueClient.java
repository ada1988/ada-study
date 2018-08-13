package org.ada.study.redisson.queue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**  
 * Filename: DelayedQueueClient.java  <br>
 *
 * Description:  客户端 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年8月13日 <br>
 *  
 */

public class DelayedQueueClient {
	
	static Integer test = 0;
	
	static String key = "delayed_queue_client";
	
	static RedissonClient redisson = null;
	
	public static RedissonClient getClient(){
		if(null!=redisson){
			return redisson;
		}
		Config config = new Config();
        config. useSingleServer().setAddress("http://192.168.10.110:6379");
        SingleServerConfig singleConfig = config.useSingleServer();
        singleConfig.setPassword("exj123321");
        redisson = Redisson.create(config);
        return redisson;
	}
	
	public static void pushMsg(String msg){
		RBlockingQueue<String> distinationQueue =  getClient().getBlockingQueue(key);
		RDelayedQueue<String> delayedQueue = getClient().getDelayedQueue(distinationQueue);
		// 10秒钟以后将消息发送到指定队列
		Integer sub = new Random().nextInt(30);
		delayedQueue.offer(sub+":"+msg, sub, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(test<100){
					String msg = "client:msg"+test+":"+System.currentTimeMillis();
					DelayedQueueClient.pushMsg(msg);
					test = test + 1;
				}
			}
		});
		thread.start();
		try {
			Thread.currentThread().sleep(1000*60*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
