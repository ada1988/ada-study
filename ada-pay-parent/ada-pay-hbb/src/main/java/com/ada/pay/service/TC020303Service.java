package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020303ReqData;
import com.ada.pay.bean.TC020303RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.3 通用(03)-资金交易状态查询
 */
@Service
public class TC020303Service extends APIDockBase<TC020303ReqData, TC020303RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
