package org.ada.study.servlet.controller;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ada.study.servlet.listener.AdaAsyncListener;

/**  
 * Filename: HelloServlet.java  <br>
 *
 * Description: 异步调用  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */
@WebServlet(name = "asyn",urlPatterns = {"/asyn"},asyncSupported=true)
public class AdaAsynServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;
	
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
	/**
	 * 可以通过Filter拦截对应的异步操作，统一处理（AsyncContext）
	 */
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
			System.out.println("=====================异步方法 ST==================");
		  	AsyncContext actx = req.startAsync();//启动异步模式，会设置对应的request、response对象
		  	actx.setTimeout(1000);//超时时间
		  	actx.addListener(new AdaAsyncListener());//添加监听器
		  	Future<String> future = executor.submit(new MyWork(actx));//线程池执行任务
		  	try {
				System.out.println("result:"+future.get());//获取任务结果
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		  	System.out.println("=====================异步方法 ET==================");
    }
	
	/**
	 *	异步调用
	 */
	class MyWork implements Callable<String>{
		AsyncContext ctx = null;//异步请求上下文
		public MyWork(AsyncContext actx){
			this.ctx = actx;
		}
		@Override
		public String call() throws Exception {
			ctx.getRequest().setAttribute( "name", "ada" );//设置参数
			boolean bol = ctx.getRequest().isAsyncStarted();//检查此请求已投入异步模式。
		  	if (bol) {
		  		ctx.dispatch("/asyn.jsp");//调整，结束请求，执行监听器对应的方法
		  	}
			return "asyn 执行成功！";
		}
		
	}

}
