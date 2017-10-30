package org.ada.study.storm.mysql.msg;

import java.io.Serializable;

import org.ada.study.storm.mysql.handler.IUrlHandler;

/**  
 * Filename: UrlHandlerMapping.java  <br>
 *
 * Description: url--handler 请求地址与处理器映射关系  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

public class UrlHandlerMapping implements Serializable{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public UrlHandlerMapping(String[] patterns, IUrlHandler urlHandler) {
		super();
		this.patterns = patterns;
		this.urlHandler = urlHandler;
	}
	private String[] patterns;
	private IUrlHandler urlHandler;
	public String[] getPatterns() {
		return patterns;
	}
	public void setPatterns(String[] patterns) {
		this.patterns = patterns;
	}
	public IUrlHandler getUrlHandler() {
		return urlHandler;
	}
	public void setUrlHandler(IUrlHandler urlHandler) {
		this.urlHandler = urlHandler;
	}
}
