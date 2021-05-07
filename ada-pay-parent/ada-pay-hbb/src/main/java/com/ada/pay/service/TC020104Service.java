package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020104ReqData;
import com.ada.pay.bean.TC020104RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-个人钱包绑卡
 */
@Service
public class TC020104Service extends APIDockBase<TC020104ReqData, TC020104RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}
}
