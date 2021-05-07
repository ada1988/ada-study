package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020120ReqData;
import com.ada.pay.bean.TC020120RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.2 分账(02)-个人网银支付
 */
@Service
public class TC020120Service extends APIDockBase<TC020120ReqData, TC020120RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
