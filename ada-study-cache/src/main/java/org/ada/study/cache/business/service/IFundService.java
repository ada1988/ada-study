package org.ada.study.cache.business.service;
/**  
 * Filename: IFundService.java  <br>
 *
 * Description:  基金 业务 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */

public interface IFundService {
	/**
	 * 通过基金编码，获取基金详情
	 * @param fundId
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月18日
	 */
	public String queryDetailById(String id);
	
	public String queryDetailById2(String id);
}
