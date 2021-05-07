package com.ada.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.TC020302ReqData;
import com.ada.pay.bean.TC020302RspData;
import com.ada.pay.common.APIDockBase;

/**   
 * 6.3 通用(03)-签署电子签章
 */
@Service
public class TC020302Service extends APIDockBase<TC020302ReqData, TC020302RspData>{

	@Value("${jiuku.bank.hbb.url}")
	private String hbbUrl;

	@Override
	protected String getUrl() {
		return hbbUrl;
	}

}
