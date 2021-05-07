package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020108ReqData;
import com.ada.pay.bean.TC020108RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.1钱包类(01)-查询账户信息
 */
@Service
public class TC020108Service extends APIDockBase<TC020108ReqData, TC020108RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
