package org.ada.study.storm.mysql.common;

import java.util.concurrent.ConcurrentHashMap;

import clojure.main;

/**  
 * Filename: UserRelation.java  <br>
 *
 * Description:  存储容器（缓存session_id与用户手机号的关系） <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

public class UserRelationGlobal {
	private static ConcurrentHashMap<String, String> sessionIdContainer = new ConcurrentHashMap<String, String>();
	private static ConcurrentHashMap<String, String> sessionIdV1Container = new ConcurrentHashMap<String, String>();
	private static ConcurrentHashMap<String, String> sessionIdV2Container = new ConcurrentHashMap<String, String>();
	
	public static String getMobileBySessionId(String sessionId) {
		if(sessionIdContainer.containsKey( sessionId )) 
			return sessionIdContainer.get( sessionId );
		else 
			return null;
	}
	public static void setSessionIdAndMobile(String sessionId,String mobile) {
		sessionIdContainer.putIfAbsent( sessionId, mobile );
	}
	
	
	public String getMobileBySessionIdV1(String sessionIdV1) {
		if(sessionIdV1Container.contains( sessionIdV1 )) return sessionIdV1Container.get( sessionIdV1 );
		else return null;
	}
	public void setSessionIdV1AndMobile(String sessionIdV1,String mobile) {
		sessionIdV1Container.putIfAbsent( sessionIdV1, mobile );
	}
	public String getMobileBySessionIdV2(String sessionIdV1) {
		if(sessionIdV2Container.contains( sessionIdV1 )) return sessionIdV2Container.get( sessionIdV1 );
		else return null;
	}
	public void setSessionIdV2AndMobile(String sessionIdV2,String mobile) {
		sessionIdV1Container.putIfAbsent( sessionIdV2, mobile );
	}
	
	public static void main(String[] args) {
		UserRelationGlobal.setSessionIdAndMobile( "1", "180000004" );
		System.out.println(">>>>"+UserRelationGlobal.getMobileBySessionId( "1" ));
	}
}
