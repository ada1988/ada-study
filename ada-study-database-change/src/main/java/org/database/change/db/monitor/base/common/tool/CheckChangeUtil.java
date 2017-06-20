package org.database.change.db.monitor.base.common.tool;

import java.util.List;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;

/**  
 * Filename: CheckChangeUtil.java  <br>
 *
 * Description: 检查数据是否变化  工具类  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月26日 <br>
 *
 *  
 */

public class CheckChangeUtil {
	
	/**
	 * 检查数据是否变化
	 * @param rowData
	 * @param excludeFields 排除字段
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月26日
	 */
	public static boolean checkChange(RowData rowData,List<String> excludeFields){
		StringBuffer before = new StringBuffer();
		//前置
		for (Column column : rowData.getBeforeColumnsList()) {
			if (excludeFields.contains( column.getName() ))
				continue;
			else
				before.append( column.getValue() );
		}
		
		StringBuffer after = new StringBuffer();
		for (Column column : rowData.getAfterColumnsList()) {
			if (excludeFields.contains( column.getName() ))
				continue;
			else
				after.append( column.getValue() ).append( "=" );
		}
		String beforeData = MD5Util.EncoderByMd5( after.toString() );
		String afterData = MD5Util.EncoderByMd5( before.toString() );
		if(!beforeData.equals( afterData )) return true;
		return false;
	}
}
