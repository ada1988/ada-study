package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020304ReqData;
import com.ada.pay.bean.TC020304RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.3通用(03)-扫码支付
 */
@Service
public class TC020304Service extends APIDockBase<TC020304ReqData, TC020304RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
