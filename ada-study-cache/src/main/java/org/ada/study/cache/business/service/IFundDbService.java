package org.ada.study.cache.business.service;

import java.util.List;

import org.ada.study.cache.business.entity.FundBean;

/**  
 * Filename: IFundService.java  <br>
 *
 * Description:  DB获取基金业务 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */

public interface IFundDbService {
	/**
	 * 通过基金编码，获取基金详情
	 * @param fundId
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月18日
	 */
	public FundBean queryDetailById(String id);
	
	/**
	 * 获取所有基金
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	public List<FundBean> queryAllFund();
}
