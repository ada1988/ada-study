package org.ada.study.cache.common.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Filename: HttpCacheFilter.java <br>
 *
 * Description: http浏览器 缓存 <br>
 * 
 * 添加装载器@ServletComponentScan，通过@WebFilter注解的过滤器，才能生效
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年6月19日 <br>
 *
 * 
 */
@WebFilter(urlPatterns = { "/fund/detail/*" }, filterName = "httpCacheFilter")
public class HttpCacheFilter implements Filter {
	
	private final Logger	logger	= LoggerFactory.getLogger( getClass() );
	
	final long	maxAge	= 60*60*1;	// 文档可以在浏览器端/proxy上缓存多久，缓存时间1小時
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// 转换请求、回复对象
		HttpServletRequest reqeust = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 当前系统时间
		long now = System.currentTimeMillis();
		/*
		 * 获取上次修改时间
		 */
		String ifModifiedSinceStr = reqeust.getHeader( "If-Modified-Since" );
		/*
		 * 首次访问，直接跳过过滤
		 */
		if (null == ifModifiedSinceStr) {
			// 设置首次访问时间
			setResponse( response, now );
			logger.error( "http缓存--首次获取DB数据--设置修改时间为：" + HolderDateFormat.get().format( new Date( now ) ) );
			chain.doFilter( reqeust, response );
		} else {
			try {
				Date ifModifiedSince = HolderDateFormat.get().parse( ifModifiedSinceStr );
				// 最终修改时间不为空 并且 修改时间+缓存时间 > 系统当前时间 进行缓存，否则跳过
				if (ifModifiedSince != null && ifModifiedSince.getTime() + maxAge * 1000 > System.currentTimeMillis()) {
					response.setStatus( HttpStatus.NOT_MODIFIED.value() );
					render( response );//渲染内容
					logger.error( "http缓存--获取缓存数据--上次修改时间：" + HolderDateFormat.get().format( ifModifiedSince ) );
				} else {
					// 设置首次访问时间
					setResponse( response, now );
					logger.error( "http缓存--缓存数据过期--设置修改时间为：" + HolderDateFormat.get().format( new Date( now ) ) );
					chain.doFilter( reqeust, response );
				}
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				chain.doFilter( reqeust, response );
			}
		}
	}

	//线程守护SimpleDateFormat对象
	ThreadLocal<DateFormat>	HolderDateFormat = new ThreadLocal<DateFormat>() {
			@Override
			protected DateFormat initialValue() {
					DateFormat dataFormat = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US );
					return dataFormat;
			}

	};

	/**
	 * 防止内存泄漏
	 */
	@Override
	public void destroy() {
		HolderDateFormat.remove();
	}

	/**
	 * 渲染页面
	 * @param response
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	public void render(HttpServletResponse response){
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		try {
		       response.getWriter().write("哎呦喂，被缓存喽");
		} catch (IOException e) {
		       e.printStackTrace();
		}
	}
	
	public HttpServletResponse setResponse(HttpServletResponse response, long now) {
		// 文档修改时间
		response.setHeader( "Last-Modified", HolderDateFormat.get().format( new Date( now ) ) );
		// 过期时间 http 1.0支持
		response.setHeader( "Expires", HolderDateFormat.get().format( new Date( now + maxAge ) ) );
		// 文档生存时间 http 1.1支持
		response.setHeader( "Cache-Control", "max-age=" + maxAge );
		// 当前系统时间
		response.setHeader( "Date", HolderDateFormat.get().format( new Date( now ) ) );

		return response;
	}
}
