package org.ada.study.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**  
 * Filename: AdaServletContextAttributeListener.java  <br>
 *
 * Description: ServletContextAttributeListener  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */

public class AdaServletContextAttributeListener implements ServletContextAttributeListener{

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		ServletContextAttributeListener.super.attributeAdded( event );
		System.out.println("上下文属性监听器：AdaServletContextAttributeListener:attributeAdded");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		ServletContextAttributeListener.super.attributeRemoved( event );
		System.out.println("上下文属性监听器：AdaServletContextAttributeListener:attributeRemoved");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		ServletContextAttributeListener.super.attributeReplaced( event );
		System.out.println("上下文属性监听器：AdaServletContextAttributeListener:attributeReplaced");
	}

}
