package org.ada.study.cache.common.constant;


/**  
 * Filename: AdaCacheConstant.java  <br>
 *
 * Description:  缓存键 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */

public class AdaCacheConstant {
	/**
	 * @Cacheable 用于标注的缓存键
	 */
	
	//基金_PC端_详情
	public final static String FUND_PC_DETAIL = "mapdb.fund.pc.detail";
	
	//默认缓存键
	public final static String DEFUALT_CACHE_KEY = "mapdb.default";
	
	/**
	 * spring 用于装载的缓存键
	 */
	public static enum AdaCacheEnum {
		默认缓存(DEFUALT_CACHE_KEY),
		基金_PC端_详情(FUND_PC_DETAIL);
		
		private AdaCacheEnum(String cacheName){
			this.cacheName = cacheName;
		}
		
		private String cacheName;

		public String getCacheName() {
			return cacheName;
		}

		public void setCacheName(String cacheName) {
			this.cacheName = cacheName;
		}
	}
}
