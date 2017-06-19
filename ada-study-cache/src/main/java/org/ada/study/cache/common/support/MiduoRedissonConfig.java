package org.ada.study.cache.common.support;

import org.redisson.Config;

public class MiduoRedissonConfig extends Config {

	@SuppressWarnings("deprecation")
	public MiduoRedissonConfig(MiduoClusterServersConfig miduoClusterServersConfig){
		super();
		useClusterServers(miduoClusterServersConfig);
	}
	
}
