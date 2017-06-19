package org.ada.study.cache.business.controller;

import org.ada.study.cache.business.entity.FundBean;
import org.ada.study.cache.business.service.IFundLocalCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**  
 * Filename: FundController.java  <br>
 *
 * Description: 基金 控制器 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */
@Controller
public class FundController {
	
	@Autowired
	private IFundLocalCacheService fundLocalCacheService;
	
	@RequestMapping("/fund/detail/{id}")
	@ResponseBody
	public FundBean detailJson(@PathVariable("id") String id){
		return fundLocalCacheService.queryLocalDetailById( id );
	}
	
	@RequestMapping("/fund/null/detail")
	@ResponseBody
	public FundBean detailKeyNullJson(){
		return fundLocalCacheService.queryLocalDetailById( null );
	}
}
