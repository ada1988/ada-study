package org.ada.study.servlet.listener;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebListener;

/**  
 * Filename: AdaAsyncListener.java  <br>
 *
 * Description: 异步监听器  <br>
 * 
 * @WebListener 不支持
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月11日 <br>
 *
 *  
 */
public class AdaAsyncListener implements AsyncListener{

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println("异步监听器：AdaAsyncListener:onComplete 一些清理工作或者其他");
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		System.out.println("异步监听器：AdaAsyncListener:onTimeout 一些超时处理的工作或者其他");
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		System.out.println("异步监听器：AdaAsyncListener:onError");
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		System.out.println("异步监听器：AdaAsyncListener:onStartAsync");
	}

}
