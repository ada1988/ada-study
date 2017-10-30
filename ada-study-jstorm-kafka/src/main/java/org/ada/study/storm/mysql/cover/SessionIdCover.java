package org.ada.study.storm.mysql.cover;
/**  
 * Filename: SessionIdCover.java  <br>
 *
 * Description:  简单处理session_id <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

public class SessionIdCover implements IFieldValueCovert{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public String valueCover(String original) {
		String[] sessions = original.split( "###" );
		for(String sessionId:sessions){
			if(null!=sessionId && !"-".equals( sessionId ) && !"".equals( sessionId ))
				return sessionId;
		}
		return "-";
	}
	
}
