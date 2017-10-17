package org.ada.study.servlet.init.base.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.ada.study.servlet.init.base.AdaWebApplicationInitializer;

/**  
 * Filename: AdaContextLoaderInitializer.java  <br>
 *
 * Description:  上下文初始化 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月12日 <br>
 *
 *  
 */

public class AdaContextLoaderInitializer implements AdaWebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("AdaContextLoaderInitializer:onStartup");
	}

}
