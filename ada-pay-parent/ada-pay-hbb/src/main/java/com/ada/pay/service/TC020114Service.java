package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020114ReqData;
import com.ada.pay.bean.TC020114RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-钱包提现
 */
@Service
public class TC020114Service extends APIDockBase<TC020114ReqData, TC020114RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
