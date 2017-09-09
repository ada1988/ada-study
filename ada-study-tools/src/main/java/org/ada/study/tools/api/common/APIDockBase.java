package org.ada.study.tools.api.common;

import org.ada.study.tools.api.model.ReqBase;
import org.ada.study.tools.api.model.RespBase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
 * @Createtime: 2017年8月16日 <br>
 *
 *  
 */

public abstract class APIDockBase<Req extends ReqBase,RespDataClass> {
	
	/**
	 * 封装 响应的泛型对应的实体
	 * @param req
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年8月16日
	 */
	protected  RespBase<RespDataClass> requestBaseApi(Req req){
		String result = null;
		try {
			result = APICommonTool.commonRequest( apiUrl() , req );
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject resultObject = JSON.parseObject( result );
		
		String code = resultObject.getString( "code" );
		String msg = resultObject.getString( "msg" );
		String data = resultObject.getString( "data" );
		
		
		RespBase<RespDataClass> resultBean = new RespBase<RespDataClass>();
		resultBean.setCode( Integer.valueOf( code ) );
		resultBean.setMsg( msg );
		resultBean.setData( JSON.parseObject( data, respDataClass() ) );
		return resultBean;
	}
	/**
	 * 强制转换的Data类型
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年7月31日
	 */
	public abstract Class<RespDataClass> respDataClass();

	/**
	 * 接口地址
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年7月31日
	 */
	public abstract String apiUrl();
	
	/**
	 * 对外开放接口(子类实现，由于requestBaseApi接口为protected修饰，对外访问受限)
	 * @param req
	 * @return
	 * @throws Exception
	 * @author: CZD  
	 * @Createtime: 2017年8月2日
	 */
	public abstract  RespBase<RespDataClass> requestAPI(Req req) throws Exception;
}
