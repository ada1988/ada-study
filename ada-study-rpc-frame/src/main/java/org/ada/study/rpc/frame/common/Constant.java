package org.ada.study.rpc.frame.common;

/**
 * Filename: Constant.java <br>
 *
 * Description: Zookeeper 公共配置<br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月17日 <br>
 *
 * 
 */

public interface Constant {
	int		ZK_SESSION_TIMEOUT	= 5000;
	String	ZK_REGISTRY_PATH	= "/registry";
	String	ZK_DATA_PATH		= ZK_REGISTRY_PATH + "/data";
}
