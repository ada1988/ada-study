package org.ada.study.redisson.topic;

import org.redisson.api.listener.MessageListener;

/**  
 * Filename: PublishTopic.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年5月7日 <br>
 *  
 */

public class AcceptTopic implements MessageListener<String>{
	
	 @Override
	    public void onMessage(String channel, String message) {
	        System.out.println("接受消息："+message);
	    }
}
