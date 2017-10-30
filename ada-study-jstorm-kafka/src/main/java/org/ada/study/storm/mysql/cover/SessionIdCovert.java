package org.ada.study.storm.mysql.cover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: NginxSessionHandler.java  <br>
 *
 * Description: cookies中获取session_id  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public class SessionIdCovert implements IFieldValueCovert{
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionIdCovert.class);
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String cookiesId;
	
	public SessionIdCovert(String cookiesId){
		this.cookiesId = cookiesId;
	}
	
	@Override
	public String valueCover(String original) {
		if(null == original || "".equals( original ))
			return "-";
		try {
			String[] keyValues = original.split( ";" );
			for(String keyValue:keyValues){
				if(keyValue.indexOf( cookiesId )>-1)
					return keyValue.split( "=" )[1];
			}
		} catch (Exception e) {
			LOGGER.error( "cookies中获取session_id,数据错误：{}", original);
			return "-";
		}
		return "-";
	}

}
