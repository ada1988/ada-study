package org.database.change.db.monitor.business.send;
/**  
 * Filename: IMQHandler.java  <br>
 *
 * Description: MQ处理器  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月20日 <br>
 *
 *  
 */

public interface IMQHandler {
	public <T> void sendRabbitMessage(T object);
}
