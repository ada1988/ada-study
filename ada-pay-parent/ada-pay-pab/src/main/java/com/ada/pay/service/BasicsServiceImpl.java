package com.ada.pay.service;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.RespBase;
import com.ada.pay.constants.PayChannelConstants;

@Service(PayChannelConstants.CHANNEL_0)
public class BasicsServiceImpl implements IBasicsService{
	private static final Logger LOG = LoggerFactory.getLogger(BasicsServiceImpl.class);
	
	public <Req> Map<String, Object> beforeRequestHandler(Req req) {
		Map<String,Object> reqData = new HashMap<String, Object>();
		return reqData;
	}

	public <Resp> RespBase<Resp> afterRequestHandler(String result,Class<Resp> clazz) {
		//未校验合法性，如是否被篡改
		RespBase<Resp> resultBean = new RespBase<Resp>();
		return resultBean;
	}

}
