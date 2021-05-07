/**  
 * @Title:  	  TC020101Service.java   <br>
 * @Package:  	  com.jiuku.modules.feign.bank.service   <br>
 * 
 * @Description:  TODO(用一句话描述该文件做什么) <br>
 *  
 * @author: 	  CZD     <br>
 * @version:	  V1.0  <br>
 * @createTime:   2021年1月4日 下午3:58:21   <br>
 */  
package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020101ReqData;
import com.ada.pay.bean.TC020101RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * @ClassName:  TC020101Service   <br>
 *
 * @Description: 6.1钱包类(01) 6.1.1 获取短信验证码(020101) <br>
 *
 * @author: CZD <br>
 * @version:V1.0  <br>
 * @createTime:   2021年1月4日 下午3:58:21    <br>
 */
@Service
public class TC020101Service extends APIDockBase<TC020101ReqData, TC020101RspData>{
	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
