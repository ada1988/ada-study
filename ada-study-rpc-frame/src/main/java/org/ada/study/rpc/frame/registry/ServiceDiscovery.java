package org.ada.study.rpc.frame.registry;

import io.netty.util.internal.ThreadLocalRandom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.ada.study.rpc.frame.common.Constant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filename: ServiceDiscovery.java <br>
 *
 * Description: 服务发现 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月17日 <br>
 *
 * 
 */

public class ServiceDiscovery {
	private static final Logger		LOGGER		= LoggerFactory.getLogger( ServiceDiscovery.class );

	private CountDownLatch			latch		= new CountDownLatch( 1 );
	private volatile List<String>	dataList	= new ArrayList<String>();
	private String registryAddress;
	public ServiceDiscovery(String registryAddress) {
        this.registryAddress = registryAddress;

        ZooKeeper zk = connectServer();
        if (zk != null) {
            watchNode(zk);
        }
    }
	/**
	 * 随机算法：获取服务地址
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年10月17日
	 */
	public String discover() {
        String data = null;
        int size = dataList.size();
        if (size > 0) {
            if (size == 1) {
                data = dataList.get(0);
                LOGGER.debug("using only data: {}", data);
            } else {
                data = dataList.get(ThreadLocalRandom.current().nextInt(size));
                LOGGER.debug("using random data: {}", data);
            }
        }
        return data;
    }

	/**
	 * 获取连接
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年10月17日
	 */
	private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            LOGGER.error("", e);
        }
        return zk;
    }
	/**
	 * 监听目录子节点结构
	 * @param zk
	 * @author: CZD  
	 * @Createtime: 2017年10月17日
	 */
	private void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(Constant.ZK_REGISTRY_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        watchNode(zk);
                    }
                }
            });
            List<String> dataList = new ArrayList<String>();
            for (String node : nodeList) {
                byte[] bytes = zk.getData(Constant.ZK_REGISTRY_PATH + "/" + node, false, null);
                dataList.add(new String(bytes));
            }
            LOGGER.debug("node data: {}", dataList);
            this.dataList = dataList;
        } catch (KeeperException | InterruptedException e) {
            LOGGER.error("", e);
        }
    }

}
