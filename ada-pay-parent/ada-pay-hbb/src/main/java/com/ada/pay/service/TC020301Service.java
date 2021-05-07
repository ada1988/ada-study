package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020301ReqData;
import com.ada.pay.bean.TC020301RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.3 通用(03)-处理日志查询
 */
@Service
public class TC020301Service extends APIDockBase<TC020301ReqData, TC020301RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
