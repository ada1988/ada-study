package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020113ReqData;
import com.ada.pay.bean.TC020113RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-钱包网银充值
 */
@Service
public class TC020113Service extends APIDockBase<TC020113ReqData, TC020113RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
