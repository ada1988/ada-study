package com.ada.pay.common;
import java.io.File;
import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.ada.pay.utils.AdaSpringContextUtils;

import feign.Feign;
import feign.Logger.Level;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;

/**  
 * Filename: APICommonTool.java  <br>
 *
 * Description:  请求工具 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2020年12月02日 <br>
 *
 *  
 */
@Component
@Import(FeignClientsConfiguration.class)
@DependsOn("adaSpringContextUtils")
public class APICommonTool {
	private static final Logger LOG = LoggerFactory.getLogger(APICommonTool.class);
	
	private DyClient dyClient;
	
	@Autowired
    public APICommonTool(Decoder decoder, Encoder encoder) {
		boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
		
		String port = AdaSpringContextUtils.applicationContext.getEnvironment().getProperty("server.port");
		StringBuilder path = new StringBuilder();
		
		path.append(File.separator).append("opt").append(File.separator).append("projects").append(File.separator)
		.append("feign_http_").append(port).append(".log");
		
		if(isWindows) {
			path = new StringBuilder("D:").append(File.separator).append("feign_http_").append(port).append(".log");
		}
		
		LOG.info("::::::::::::::::::::::::::::Ada特别提醒，HTTP请求日志记录路径为：{}:::::::::::::::::::::::::::::::::::",path);
		
		dyClient = Feign.builder().logLevel(Level.FULL).logger(new feign.Logger.JavaLogger().appendToFile(path.toString()))
				 .encoder(new JacksonEncoder()).target(Target.EmptyTarget.create(DyClient.class));
    }
	/**
	 * HttpClient调用
	 * 
	 * @param url 地址
	 * @param req 请求
	 * @return
	 * @throws Exception
	 * @author: CZD  
	 * @Createtime:  2020年12月02日
	 */
	public String commonRequest(URI baseUri,Map<String,Object> req) throws Exception{
		return dyClient.commonRequest(baseUri, req);
	}
	
}