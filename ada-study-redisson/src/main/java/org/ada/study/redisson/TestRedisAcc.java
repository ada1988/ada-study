package org.ada.study.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**  
 * Filename: TestRedisAcc.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年6月2日 <br>
 *  
 */

public class TestRedisAcc {
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
	
	public static long getAutoNumber(Long initNum,String key){
		RAtomicLong atomic = getClient().getAtomicLong(key);
		if(!atomic.isExists()){
			atomic.set(initNum);
		}
		return atomic.addAndGet(1);
	}
	
	public static void main(String[] args) {
		System.out.println(getAutoNumber(10000L,"订单"));
		
	}
}
