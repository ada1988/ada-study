package org.ada.study.redisson;

import java.util.concurrent.TimeUnit;

import org.ada.study.redisson.topic.AcceptTopic;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**
 * Hello world!
 *
 */
public class TestApp 
{
	public static Integer num=0;
	/**
	 * 获取redis客户端
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月26日
	 */
	public static RedissonClient getClient(){
		Config config = new Config();
        config. useSingleServer().setAddress("http://192.168.10.110:6379");
        SingleServerConfig singleConfig = config.useSingleServer();
        singleConfig.setPassword("exj123321");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
	}
	
	
	public static void pubMsg(){
		RedissonClient redisson = getClient();
		// 在其他线程或JVM节点
		RTopic<String> topic = redisson.getTopic("pay-center:order:dispatch");
		long clientsReceivedMessage = topic.publish("发消息");
		System.out.println("发送成功："+clientsReceivedMessage);
	}
	
    public static void main( String[] args )
    {
    	RLock rlock = getClient().getLock("222");
    	MultThread thread1 = new MultThread(rlock);
    	new Thread(thread1).start();
    	RLock lock = getClient().getLock("222");
    	MultThread thread = new MultThread(lock);
    	new Thread(thread).start();
    	/*RLock rlock = getClient().getLock("222");
    	try {
			rlock.tryLock(3, 50, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
    }
}
