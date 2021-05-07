package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020124ReqData;
import com.ada.pay.bean.TC020124RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-钱包协议充值
 */
@Service
public class TC020124Service extends APIDockBase<TC020124ReqData, TC020124RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
