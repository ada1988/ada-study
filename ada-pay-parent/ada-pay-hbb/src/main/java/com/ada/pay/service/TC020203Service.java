package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020203ReqData;
import com.ada.pay.bean.TC020203RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.2 分账(02)-结算
 */
@Service
public class TC020203Service extends APIDockBase<TC020203ReqData, TC020203RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}
}
