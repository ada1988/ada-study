package org.ada.study.servlet.listener;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**  
 * Filename: AdaServletContextListener.java  <br>
 *
 * Description:  ServletContextListener  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */
@WebListener
public class AdaServletContextListener implements ServletContextListener{

	/**
	 * 该方法可以实现 servlet ，硬编码写入容器
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized( sce );
		System.out.println("上下文监听器：AdaServletContextListener:contextInitialized");
		//initDynServlet( sce );//硬编码方式注入servlet
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed( sce );
		System.out.println("上下文监听器：AdaServletContextListener:contextDestroyed");
	}

	
	public void initDynServlet(ServletContextEvent sce){
		ServletContext sc = sce.getServletContext(); 

        // Register Servlet 
        ServletRegistration sr = sc.addServlet("DynamicServlet", 
            "web.servlet.dynamicregistration_war.TestServlet"); 
        sr.setInitParameter("servletInitName", "servletInitValue"); 
        sr.addMapping("/*"); 

        // Register Filter 
        FilterRegistration fr = sc.addFilter("DynamicFilter", 
            "web.servlet.dynamicregistration_war.TestFilter"); 
        fr.setInitParameter("filterInitName", "filterInitValue"); 
        fr.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), 
                                     true, "DynamicServlet"); 

        // Register ServletRequestListener 
        sc.addListener("web.servlet.dynamicregistration_war.TestServletRequestListener"); 
	}
}
