package org.ada.study.cache.config.sohu;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**  
 * Filename: CacheCloudProperties.java  <br>
 *
 * Description: 搜狐设置项  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月13日 <br>
 *
 *  
 */
@ConfigurationProperties("sohu.cache.cloud")
public class CacheCloudProperties {
	
	private Integer scanInterval;
	
	private Long appid;
	
	private String lockName;
	
	private String mode;

	public Long getAppid() {
		return appid;
	}

	public void setAppid(Long appid) {
		this.appid = appid;
	}

	public Integer getScanInterval() {
		return scanInterval;
	}

	public void setScanInterval(Integer scanInterval) {
		this.scanInterval = scanInterval;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
