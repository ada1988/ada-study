package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020121ReqData;
import com.ada.pay.bean.TC020121RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.2 分账(02)-企业网银支付
 */
@Service
public class TC020121Service extends APIDockBase<TC020121ReqData, TC020121RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
