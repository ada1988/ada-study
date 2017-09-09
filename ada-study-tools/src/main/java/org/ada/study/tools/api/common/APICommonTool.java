package org.ada.study.tools.api.common;

import org.ada.study.tools.api.model.ReqBase;

/**  
 * Filename: APICommonTool.java  <br>
 *
 * Description:  请求工具 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月16日 <br>
 *
 *  
 */

public class APICommonTool {
	/**
	 * HttpClient调用
	 * 
	 * @param url 地址
	 * @param req 请求
	 * @return
	 * @throws Exception
	 * @author: CZD  
	 * @Createtime: 2017年8月16日
	 */
	public static <Req extends ReqBase> String commonRequest(String url,Req req) throws Exception{
		System.out.println("实现对应的加密，解密封装，HTTP请求等");
		return null;
	}
}
