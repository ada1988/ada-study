package org.ada.study.servlet.init.base.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.ada.study.servlet.init.base.AdaWebApplicationInitializer;

/**  
 * Filename: AdaAnnotationConfigDispatcherServletInitializer.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月12日 <br>
 *
 *  
 */

public class AdaAnnotationConfigDispatcherServletInitializer implements AdaWebApplicationInitializer{
	/**
	 * 通过Class.forName(,false);装载内部定义的接口，外部jar对应的实现，插拔式引入外部拓展
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("AdaAnnotationConfigDispatcherServletInitializer:onStartup");
	}

}
