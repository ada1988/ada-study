package org.ada.study.cache.common.support;

import java.io.IOException;

import org.redisson.ClusterServersConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sohu.tv.cachecloud.client.basic.heartbeat.ClientStatusEnum;
import com.sohu.tv.cachecloud.client.basic.heartbeat.HeartbeatInfo;
import com.sohu.tv.cachecloud.client.basic.util.ConstUtils;
import com.sohu.tv.cachecloud.client.basic.util.HttpUtils;

public class AdaClusterServersConfig extends ClusterServersConfig {
	
	private Logger logger = LoggerFactory.getLogger(AdaClusterServersConfig.class);

	private String appId;
	
	public AdaClusterServersConfig(String appId,Integer scanInterval){
		super();
		setScanInterval(scanInterval);
		this.appId = appId;
		String url = String.format(ConstUtils.REDIS_CLUSTER_URL, String.valueOf(appId));
        String response = HttpUtils.doGet(url);
        ObjectMapper objectMapper = new ObjectMapper();
        HeartbeatInfo heartbeatInfo = null;
        try {
            heartbeatInfo = objectMapper.readValue(response, HeartbeatInfo.class);
        } catch (IOException e) {
            logger.error("remote build error, appId: {}", appId, e);
        }
        /** 检查客户端版本 **/
        if (heartbeatInfo.getStatus() == ClientStatusEnum.ERROR.getStatus()) {
            throw new IllegalStateException(heartbeatInfo.getMessage());
        } else if (heartbeatInfo.getStatus() == ClientStatusEnum.WARN.getStatus()) {
            logger.warn(heartbeatInfo.getMessage());
        } else {
            logger.info(heartbeatInfo.getMessage());
        }
        //形如 ip1:port1,ip2:port2,ip3:port3
        String nodeInfo = heartbeatInfo.getShardInfo();
        //为了兼容,如果允许直接nodeInfo.split(" ")
        nodeInfo = nodeInfo.replace(" ", ",");
        String[] nodeArray = nodeInfo.split(",");
		addNodeAddress(nodeArray);
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
