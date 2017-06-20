package org.database.change.db.monitor.start;

import java.net.InetSocketAddress;

import org.database.change.db.monitor.base.common.MdObservable;
import org.database.change.db.monitor.business.DbSubject;
import org.database.change.db.monitor.business.observable.StatisticChangeObservable;
import org.database.change.db.monitor.business.send.IMQHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;

/**  
 * Filename: DbMonitorStart.java  <br>
 *
 * Description: DB监听器启动类  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月20日 <br>
 *
 *  
 */
@Component("dbMonitorStart")
public class DbMonitorStart {
	
	@Autowired
	@Qualifier("productSender")
	private IMQHandler productSender;
	
	@Autowired
	@Qualifier("statisticSender")
	private IMQHandler statisticSender;
	
	@Value("${canal.zookeeper.host}")
	private String	host;
	@Value("${canal.zookeeper.post}")
	private Integer	post;
	@Value("${canal.destination}")
	private String	destination;
	@Value("${canal.username}")
	private String	username;
	@Value("${canal.password}")
	private String	password;
	@Value("${database.interval.times}")
	private long intervalTimes;
	@Value("${canal.client.type}")
	private String	type;
	
	
	public void start(){
        CanalConnector connector = null;
		if (null != type && type.equals( "zookeeper" ))
        	connector = CanalConnectors.newClusterConnector( host+":"+post, destination, username, password);
        else
        	connector = CanalConnectors.newSingleConnector(new InetSocketAddress(host,
            		post), destination, username, password);
		DbSubject subject = new DbSubject( connector, destination, intervalTimes );
		
		//监听者
		MdObservable<Message> statisticChangeHandler = new StatisticChangeObservable(statisticSender);
		subject.subscribe( statisticChangeHandler );
		subject.start();
	}
	
	public static void main(String[] args) {
		new DbMonitorStart().start();
	}
	
}
