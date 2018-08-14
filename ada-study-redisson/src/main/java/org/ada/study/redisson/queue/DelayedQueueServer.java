package org.ada.study.redisson.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**  
 * Filename: DelayedQueueServer.java  <br>
 *
 * Description:  服务端 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年8月13日 <br>
 *  
 */

public class DelayedQueueServer {
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
	
	public static void pushMsg(){
		RBlockingQueue<String> distinationQueue =  getClient().getBlockingQueue(key);
		try {
			System.out.println("等待中....."+System.currentTimeMillis());
			String takeMsg = distinationQueue.take();
			String[] msgs = takeMsg.split(":");
			Long sumTime = System.currentTimeMillis()-new Long(msgs[msgs.length-1]);
			System.out.println("server:"+takeMsg+"  间隔时间："+sumTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					pushMsg();
				}
			}
		}).start();
		
		try {
			Thread.currentThread().sleep(1000*60*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
