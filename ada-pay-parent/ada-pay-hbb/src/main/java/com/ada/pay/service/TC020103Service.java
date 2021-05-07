package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020103ReqData;
import com.ada.pay.bean.TC020103RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-企业开通钱包
 */
@Service
public class TC020103Service extends APIDockBase<TC020103ReqData, TC020103RspData>{
	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
