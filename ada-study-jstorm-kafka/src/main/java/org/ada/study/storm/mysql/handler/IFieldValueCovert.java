package org.ada.study.storm.mysql.handler;

import java.io.Serializable;

/**  
 * Filename: IFieldValueCover.java  <br>
 *
 * Description: 字段强制转换  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public interface IFieldValueCovert extends Serializable{
	public String valueCover(String original);
}
