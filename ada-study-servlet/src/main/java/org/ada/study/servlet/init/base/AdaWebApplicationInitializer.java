package org.ada.study.servlet.init.base;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**  
 * Filename: AdaWebApplicationInitializer.java  <br>
 *
 * Description: 自定义启动接口  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月12日 <br>
 *
 *  
 */

public interface AdaWebApplicationInitializer {
	void onStartup(ServletContext servletContext) throws ServletException;
}
