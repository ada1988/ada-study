package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020107ReqData;
import com.ada.pay.bean.TC020107RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-重置交易密码
 */
@Service
public class TC020107Service extends APIDockBase<TC020107ReqData, TC020107RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
