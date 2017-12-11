package com.miduo.channel.account.common.constant;
/**  
 * Filename: MiduoCacheConstant.java  <br>
 *
 * Description:  缓存 常数：用于注解使用;枚举，用于注入spring  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月19日 <br>
 *
 *  
 */

public class MiduoCacheConstant {
	
	public static final Long TIME_OUT = 60*60*1L;//默认超时时间1小时(单位秒)
	
	public static final long LOGIN_ERROR_TIME_OUT = 60*30L;//默认超时时间半小时小时(单位秒)
	
	public static final String CACHE_BASE = "redis.";//需要手动修改，否则缓存地址无法获取
	
	
	public static final String WWW_DEFAULT = CACHE_BASE+"www.default";
	
	public static final String WWW_ERROR_LOGIN = CACHE_BASE+"www.account.login.error.";
	
	public static final String WWW_ACCOUNT_LOGIN = CACHE_BASE+"www.account.login";
	
	public enum MiduoCacheEnum {
		
		PC_默认(WWW_DEFAULT,TIME_OUT),
		PC_用户_登录(WWW_ACCOUNT_LOGIN,TIME_OUT);
		
		
		private MiduoCacheEnum(String cacheName,Long timeOut){
			this.cacheName = cacheName;
			this.timeOut = timeOut;
		}
		
		private String cacheName;
		private Long timeOut;

		public String getCacheName() {
			return cacheName;
		}

		public void setCacheName(String cacheName) {
			this.cacheName = cacheName;
		}

		public Long getTimeOut() {
			return timeOut;
		}

		public void setTimeOut(Long timeOut) {
			this.timeOut = timeOut;
		}
		
	}

}
