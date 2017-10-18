package org.ada.study.rpc.business.service;

import org.ada.study.rpc.frame.common.annotation.RpcService;

/**  
 * Filename: HelloService.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月17日 <br>
 *
 *  
 */
@RpcService(IHelloService.class) // 指定远程接口
public class HelloServiceImpl implements IHelloService{

	@Override
	public String hello(String name) {
		return "Hello! " + name;
	}

}
