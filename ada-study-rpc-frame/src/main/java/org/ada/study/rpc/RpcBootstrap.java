package org.ada.study.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**  
 * Filename: RpcBootstrap.java  <br>
 *
 * Description:  启动类 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月17日 <br>
 *
 *  
 */

public class RpcBootstrap {
	public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-provider.xml");
    }
}
