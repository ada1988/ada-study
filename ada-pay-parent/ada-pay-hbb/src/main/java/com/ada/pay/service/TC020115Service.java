package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020115ReqData;
import com.ada.pay.bean.TC020115RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-绑定卡查询
 */
@Service
public class TC020115Service extends APIDockBase<TC020115ReqData, TC020115RspData>{
	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}
}
