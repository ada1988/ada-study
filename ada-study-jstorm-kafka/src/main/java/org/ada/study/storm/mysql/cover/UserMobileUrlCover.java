package org.ada.study.storm.mysql.cover;

import org.ada.study.storm.mysql.common.UserRelationGlobal;

/**  
 * Filename: ProductUrlCover.java  <br>
 *
 * Description:  通过session_id获取用户手机号 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

public class UserMobileUrlCover implements IFieldValueCovert{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public String valueCover(String original) {
		String mySessionId = null;
		String[] sessions = original.split( "###" );
		for(String sessionId:sessions){
			if(null!=sessionId && !"-".equals( sessionId ) && !"".equals( sessionId ))
				mySessionId = sessionId;
		}
		String mobile = UserRelationGlobal.getMobileBySessionId( mySessionId );
		return null==mobile?"-":mobile;
	}

}
