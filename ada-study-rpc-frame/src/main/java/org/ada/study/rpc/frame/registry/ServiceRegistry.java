package org.ada.study.rpc.frame.registry;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.ada.study.rpc.frame.common.Constant;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: ServiceRegistry.java  <br>
 *
 * Description: 注册服务  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月17日 <br>
 *
 *  
 */

public class ServiceRegistry {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);

    private CountDownLatch latch = new CountDownLatch(1);
    
    private String registryAddress;

    public ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public void register(String data) {
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null) {
                createNode(zk, data);
            }
        }
    }
    
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
           e.printStackTrace();
        }
        return zk;
    }
    
    /**
     * 创建目录节点
     * @param zk
     * @param data
     * @author: CZD  
     * @Createtime: 2017年10月17日
     */
    private void createNode(ZooKeeper zk, String data) {
        try {
            byte[] bytes = data.getBytes();
            String path = zk.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            LOGGER.debug("create zookeeper node ({} => {})", path, data);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
