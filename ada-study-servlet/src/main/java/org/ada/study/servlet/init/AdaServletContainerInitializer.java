package org.ada.study.servlet.init;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.ada.study.servlet.init.base.AdaWebApplicationInitializer;

/**  
 * Filename: AdaServletContainerInitializer.java  <br>
 *
 * Description: 初始化转发器服务到容器  <br>
 * 
 * 转发器服务：dispatch
 * 
 * @HandlesTypes:Annotation（注解驱动），用于处理对应的AdaWebApplicationInitializer接口的实现
 * 
 * spring中的对应实现：
 * @see org.springframework.web.context.AbstractContextLoaderInitializer
 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer
 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
 * 
 * 总结： 初始化上下文的监听器；
 * 		注册一个总的请求转发器的servlet的初始值；
 * 		注册注解驱动的请求转发器的初始值；
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */
@HandlesTypes(AdaWebApplicationInitializer.class)
public class AdaServletContainerInitializer implements ServletContainerInitializer{
	
	@Override
	public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
		//定义有序集合，顺序初始化对象
		List<AdaWebApplicationInitializer> initializers = new LinkedList<AdaWebApplicationInitializer>();
		
		if(null!=webAppInitializerClasses && webAppInitializerClasses.size() > 0){
			System.out.println("AdaServletContainerInitializer:onStartup");
			//遍历所有实现了AdaWebApplicationInitializer 接口的实现
			for (Class<?> waiClass : webAppInitializerClasses) {
				//该实现方法，禁止为接口、禁止为抽象方法、并且实现了该接口AdaWebApplicationInitializer
				if(!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers())&&AdaWebApplicationInitializer.class.isAssignableFrom(waiClass)){
					try {
						initializers.add((AdaWebApplicationInitializer) waiClass.newInstance());
					}catch (Throwable ex) {
						throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
					}
				}
			}
		}
		
		
		if (initializers.isEmpty()) {
			servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
			return;
		}
		
		servletContext.log("Spring WebApplicationInitializers detected on classpath: " + initializers);

		for (AdaWebApplicationInitializer initializer : initializers) {
			initializer.onStartup(servletContext);//初始化具体组件
		}
	}

}
