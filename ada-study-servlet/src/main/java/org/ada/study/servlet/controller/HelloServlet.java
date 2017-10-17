package org.ada.study.servlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  
 * Filename: HelloServlet.java  <br>
 *
 * Description: 简单测试  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */
@WebServlet(name = "MyServlet",urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        System.out.println("打印");
        out.write("hello heroku".getBytes());
        out.flush();
        out.close();
    }

}
