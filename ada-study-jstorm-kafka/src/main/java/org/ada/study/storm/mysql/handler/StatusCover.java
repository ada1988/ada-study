package org.ada.study.storm.mysql.handler;
/**  
 * Filename: StatusCover.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public class StatusCover implements IFieldValueCovert{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public String valueCover(String original) {
		if(null == original || "".equals( original )){
			return "";
		}
		if(original.length()>16)
			original = original.substring( 0,15 );
		return original;
	}

}
