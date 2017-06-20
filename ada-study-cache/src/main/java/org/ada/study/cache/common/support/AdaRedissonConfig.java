package org.ada.study.cache.common.support;

import org.redisson.Config;

public class AdaRedissonConfig extends Config {

	@SuppressWarnings("deprecation")
	public AdaRedissonConfig(AdaClusterServersConfig miduoClusterServersConfig){
		super();
		useClusterServers(miduoClusterServersConfig);
	}
	
}
