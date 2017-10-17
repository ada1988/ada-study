package org.ada.study.servlet.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**  
 * Filename: AdaAsyncListener.java  <br>
 *
 * Description: ServletRequest监听器  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */
@WebListener
public class AdaServletRequestListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletRequestListener.super.requestDestroyed( sre );
		System.out.println("请求监听器：AdaServletRequestListener:requestDestroyed");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequestListener.super.requestInitialized( sre );
		System.out.println("请求监听器：AdaServletRequestListener:requestInitialized");
	}

}
