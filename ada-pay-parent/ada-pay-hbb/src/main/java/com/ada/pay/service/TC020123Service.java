package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020123ReqData;
import com.ada.pay.bean.TC020123RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-签约确认
 */
@Service
public class TC020123Service extends APIDockBase<TC020123ReqData, TC020123RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
