package com.ada.pay.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ada.pay.bean.RespBase;
import com.ada.pay.service.IBasicsService;
import com.alibaba.fastjson.JSON;

/**  
 * Filename: APIDockBase.java  <br>
 *
 * Description: 接口对接工具  <br>
 * 
 * 规范接口地址
 * 规范请求实体
 * 规范返回实体
 * 规范对外暴露方法
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime:  2020年12月02日 <br>
 */
public abstract class APIDockBase<ReqDataClass,RespDataClass> {
	private static final Logger LOG = LoggerFactory.getLogger(APIDockBase.class);
	
	@Autowired
	private IBasicsService basicsService;
	
	@Autowired
	private APICommonTool apiCommonTool;
	/**
	 * 封装 响应的泛型对应的实体 
	 * @param req
	 * @param payChannel 为空时仅支持一种支付渠道
	 * @return
	 */
	public RespDataClass requestApi(ReqDataClass req){
		String result = null;
		RespBase<RespDataClass> resultBean = null;
		try {
			LOG.info("{}:原始发起请求地址：{},请求参数：{}",new Date(),getUrl(),JSON.toJSONString(req));
			Map<String,Object> reqData = basicsService.beforeRequestHandler(req);
			LOG.info("服务请求:前置处理====发起请求地址：{},请求参数：{}",getUrl(),JSON.toJSONString(reqData));
			result = apiCommonTool.commonRequest( new URI(getUrl()) , reqData);
			LOG.info("原始相应请求结果：{}",result);
			resultBean = basicsService.afterRequestHandler(result, getRespClass());
			LOG.info("服务请求:后置处理====相应请求结果：{}",JSON.toJSONString(resultBean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultBean.getResData();
	}
	
	private Class<RespDataClass> getRespClass() {
		Type t = getClass().getGenericSuperclass();    
        ParameterizedType p = (ParameterizedType) t ;    
        @SuppressWarnings("unchecked")
		Class<RespDataClass> c = (Class<RespDataClass>) p.getActualTypeArguments()[1];  
        return c;
	}
	/**
	 * 
	 * @Description:    接口编码
	 *
	 * @param: 			@return      
	 * @return: 		String      
	 * @throws   
	 *
	 * @author: 	    CZD     <br>
	 * @createTime:     2021年1月4日 下午7:13:12   <br>
	 */
	protected abstract String getUrl();
}
