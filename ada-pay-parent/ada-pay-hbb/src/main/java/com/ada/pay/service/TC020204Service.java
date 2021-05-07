package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020204ReqData;
import com.ada.pay.bean.TC020204RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.2 分账(02)-开通账簿
 */
@Service
public class TC020204Service extends APIDockBase<TC020204ReqData, TC020204RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
