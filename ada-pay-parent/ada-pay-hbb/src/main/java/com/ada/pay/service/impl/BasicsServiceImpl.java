package com.ada.pay.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.pay.bean.RespBase;
import com.ada.pay.constants.PayChannelConstants;
import com.ada.pay.service.IBasicsService;
import com.ada.pay.utils.AES;
import com.ada.pay.utils.RSA;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service(PayChannelConstants.CHANNEL_1)
public class BasicsServiceImpl implements IBasicsService{
	private static final Logger LOG = LoggerFactory.getLogger(BasicsServiceImpl.class);
	@Value("${jiuku.bank.hbb.version}")
	private String version;
	@Value("${jiuku.bank.hbb.platformNo}")
	private String platformNo;
	@Value("${jiuku.bank.hbb.privateKey}")
	private String privateKey;
	@Value("${jiuku.bank.hbb.publicKey}")
	private String publicKey;
	@Value("${jiuku.bank.hbb.pwd}")
	private String pwd;
	
	public <Req> Map<String, Object> beforeRequestHandler(Req req) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String timestamp = df.format(new Date());
		String secret = AES.getKeyByPass(pwd);//玖库平台的私钥串
		String encryptionSecret = RSA.encrypt(secret, publicKey);//私钥进行加密，避免私钥明文暴露在网络环境中进行传输
		LOG.info("第一步：秘钥串进行加密。结果：{}",encryptionSecret);
		//参数重新丢弃父类属性值,获取子类所有属性
		@SuppressWarnings("unchecked")
		Map<String,Object> reqPlaintext = JSON.parseObject(JSON.toJSONString(req), Map.class);
		
		//重新拼装请求接口的数据结构
		Map<String,Object> reqData = new HashMap<String, Object>();
		reqData.put("tradeCode", reqPlaintext.get("tradeCode"));
		reqData.put("reqNo", reqPlaintext.get("reqNo"));
		reqData.put("reqTime", timestamp);
		reqData.put("secret", encryptionSecret);
		reqData.put("version", version);
		reqData.put("merchantNo", platformNo);
		
		reqPlaintext.remove("reqData");
		reqPlaintext.remove("merchantNo");
		reqPlaintext.remove("reqTime");
		reqPlaintext.remove("reqNo");
		reqPlaintext.remove("version");
		reqPlaintext.remove("plaintext");
		reqPlaintext.remove("data");
		reqPlaintext.remove("tradeCode");
		reqPlaintext.remove("secret");
		
		String encryptionData = AES.encrypt(JSON.toJSONString(reqPlaintext), secret);//通过平台秘钥串，对传输内容进行处理
		LOG.info("第二步：传输数据进行加密。结果：{}",encryptionData);
		reqData.put("data", encryptionData);
		reqData.put("plaintext", JSON.toJSONString(reqPlaintext));
		return reqData;
	}

	public <Resp> RespBase<Resp> afterRequestHandler(String result,Class<Resp> clazz) {
		JSONObject resultObject = JSON.parseObject( result );
		//未校验合法性，如是否被篡改
		RespBase<Resp> resultBean = new RespBase<Resp>();
				
		if(null != resultObject.getString( "data" )) {
				String bankSecret = RSA.decrypt(resultObject.getString( "secret" ), privateKey);
				String deData = AES.decrypt(resultObject.getString( "data" ), bankSecret);
				resultBean.setResData(JSON.parseObject( deData, clazz ));
		}
		return resultBean;
	}

}
