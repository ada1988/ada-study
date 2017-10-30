package org.ada.study.storm.mysql.cover;
/**  
 * Filename: PostParamCover.java  <br>
 *
 * Description: 简单post参数处理  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

public class PostParamCover implements IFieldValueCovert{
	private String paramName;
	public PostParamCover(String paramName){
		this.paramName = paramName;
	}
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public String valueCover(String original) {
		String[] userAndPwdStrs = original.split( "&" );
		for(String userAndPwdStr:userAndPwdStrs){
			String[] userPwd = userAndPwdStr.split( "=" );
			if(null!=userPwd[0] && userPwd[0].equals( paramName )){
				return userPwd[1];
			}
		}
		return "-";
	}
	
}
