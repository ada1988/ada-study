package org.ada.study.cache.business.service;

import org.ada.study.cache.business.entity.FundBean;

/**  
 * Filename: IFundLocalCacheService.java  <br>
 *
 * Description:  本地缓存中获取基金业务 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */

public interface IFundLocalCacheService {
	/**
	 * 通过基金编码，获取基金详情
	 * @param fundId
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月18日
	 */
	public FundBean queryLocalDetailById(String id);
	
	/**
	 * 更新本地缓存
	 * @param id
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	public FundBean updateLocalDetailById(String id);
}
