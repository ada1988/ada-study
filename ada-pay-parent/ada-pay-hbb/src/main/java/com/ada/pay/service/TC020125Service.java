package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020125ReqData;
import com.ada.pay.bean.TC020125RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-钱包协议支付
 */
@Service
public class TC020125Service extends APIDockBase<TC020125ReqData, TC020125RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
