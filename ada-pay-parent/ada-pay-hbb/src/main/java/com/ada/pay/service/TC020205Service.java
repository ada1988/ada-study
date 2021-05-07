package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020205ReqData;
import com.ada.pay.bean.TC020205RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.2 分账(02)-订单清分
 */
@Service
public class TC020205Service extends APIDockBase<TC020205ReqData, TC020205RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}
}
