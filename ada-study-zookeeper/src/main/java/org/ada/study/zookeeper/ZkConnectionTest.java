package org.ada.study.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.Stat;

/**
 * 简单连接zookeeper
 * 
 * 问题：zk异步连接，导致使用时未装配就绪
 * 解决：countDownLatch 监听连接状态，如果连接状态为已连接，countDown(),否则一直等待。
 *
 */
public class ZkConnectionTest 
{
	private static int sessionTimeout = 30000;
	private static ZooKeeper zk = null;
	static CountDownLatch connectedLatch = new CountDownLatch(1);
    public static void main( String[] args )
    {
        try {
			zk = new ZooKeeper( "192.168.30.128:2181", sessionTimeout, new Watcher(){
				@Override
				public void process(WatchedEvent event) {
					System.out.println(event);
					if (event.getState() == KeeperState.SyncConnected) {  
						connectedLatch.countDown();
					}
					
				}
			} );
			//等待连接完成
			if (States.CONNECTING == zk.getState()) {  
	            try {  
	                connectedLatch.await();  
	            } catch (InterruptedException e) {  
	                throw new IllegalStateException(e);  
	            }  
	        }  
			Stat stat = zk.exists( "/register", true );
			System.out.println(stat);
			if(null == stat)
				zk.create( "/register", "192.168.4.1:8001".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL );
			zk.setData( "/register", "192.168.4.1:8000".getBytes(), 1 );
			byte[] data = zk.getData( "/register", false ,new Stat());
			System.out.println(new String(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    static class ConnectionThread implements Runnable{
    	public void run(){
    		System.out.println("检查状态....");
    		if (States.CONNECTING == zk.getState()) {  
    			connectedLatch.countDown(); 
    			System.out.println("已就绪");
    		}
    	}
    }
}
