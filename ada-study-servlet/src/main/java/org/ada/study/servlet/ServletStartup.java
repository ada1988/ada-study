package org.ada.study.servlet;

import java.io.File;
import java.net.URL;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**  
 * Filename: WebApplicationStartup.java  <br>
 *
 * Description: embedded-tomcat  <br>
 * 
 * 嵌入式tomcat启动
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */

public class ServletStartup {
	public static void main(String[] args) throws Exception {

        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        //配置默认连接器：否则无法访问(或者直接调用tomcat.getConnector();方法，该方法添加默认连接器，并使用tomcat配置的端口)
        Connector connector = new Connector( "HTTP/1.1" );
        connector.setPort( 9090 );
        tomcat.setConnector( connector );

        //默认docBase地址（相对地址：项目地址/src/main/webapp/）
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        //编译文件路径（相对地址：项目地址/target/classes）
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);//代表一个完整webWeb应用程序资源
        //定义一个WEB-INF/classes下的web资源，为target/classes路径的资源
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
        tomcat.start();
        //查看资源地址
        System.out.println("=====================资源地址 ST==================");
        for(URL url:ctx.getResources().getBaseUrls()){
        	System.out.println("resource:"+url.getPath());
        }
        System.out.println("=====================资源地址 ET==================");
        tomcat.getServer().await();
    }
}
