package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020106ReqData;
import com.ada.pay.bean.TC020106RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-解除钱包绑定的银行卡
 */
@Service
public class TC020106Service extends APIDockBase<TC020106ReqData, TC020106RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
