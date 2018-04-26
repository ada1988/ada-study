package org.ada.study.redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Hello world!
 *
 */
public class App 
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
        config. useSingleServer().setAddress("http://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
	}
	
    public static void main( String[] args )
    {
    	RLock lock = getClient().getLock("anyLock");
    	MultThread thread1 = new MultThread( lock );
    	MultThread thread2 = new MultThread( lock );
    	MultThread thread3 = new MultThread( lock );
    	MultThread thread4 = new MultThread( lock );
    	
    	new Thread(thread1).start();
    	new Thread(thread2).start();
    	new Thread(thread3).start();
    	new Thread(thread4).start();
    
    }
}
