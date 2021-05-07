package com.ada.pay.service;

import java.util.Map;

import com.ada.pay.bean.RespBase;

/**
 * 
 * 基础服务抽离:发起请求的前置、后置拦截处理器
 * 
 * @author CZD
 *
 */

public interface IBasicsService {
	
	/**
	 * 服务请求:前置处理。  如获取公钥、私有、数据加密等
	 * @param <Req>
	 * @param req
	 * @return
	 */
	public <Req> Map<String,Object> beforeRequestHandler(Req req);
	
	/**
	 * 服务请求:后置处理。如解密、转换返回实体等
	 * @param <Resp>
	 * @param resp
	 * @param clazz
	 * @return
	 */
	public <Resp> RespBase<Resp> afterRequestHandler(String result,Class<Resp> clazz);
	
}
