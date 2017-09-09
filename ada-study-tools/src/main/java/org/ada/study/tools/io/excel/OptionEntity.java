package org.ada.study.tools.io.excel;

import org.apache.poi.ss.usermodel.Row;


/**  
 * Filename: OptionEntity.java  <br>
 *
 * Description: 组装实体 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年4月19日 <br>
 *
 *  
 */

public interface OptionEntity<Q> {
	public Q option(Row row);
}
