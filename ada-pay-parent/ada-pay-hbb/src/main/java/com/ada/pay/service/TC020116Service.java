package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020116ReqData;
import com.ada.pay.bean.TC020116RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-钱包余额支付
 */
@Service
public class TC020116Service extends APIDockBase<TC020116ReqData, TC020116RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}
}
