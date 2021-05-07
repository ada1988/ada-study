package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020305ReqData;
import com.ada.pay.bean.TC020305RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.3通用(03)-扫码支付查询
 */
@Service
public class TC020305Service extends APIDockBase<TC020305ReqData, TC020305RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;	
	}

}
