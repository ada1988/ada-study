package org.database.change.db.monitor.business.handler;

import org.database.change.db.monitor.base.common.MdOptionsHandler;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;

/**  
 * Filename: CommonHandler.java  <br>
 *
 * Description:  产品公用处理器 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月20日 <br>
 *
 *  
 */

public class CommonProductHandler implements MdOptionsHandler<String>{

	@Override
	public String deleteOption(RowData rowData) {
		for(Column column : rowData.getBeforeColumnsList()){
			if(column.getName().equals( "product_id" ))
				return column.getValue();
		}
		return null;
	}

	@Override
	public String updateOption(RowData rowData) {
		System.out.println("=============================");
		System.out.println(">>>>>>>>>>>>>>>>"+rowData);
		System.out.println("=============================");
		for(Column column : rowData.getBeforeColumnsList()){
			if(column.getName().equals( "product_id" ))
				return column.getValue();
		}
		return null;
	}

	@Override
	public String insertOption(RowData rowData) {
		for(Column column : rowData.getAfterColumnsList()){
			if(column.getName().equals( "product_id" ))
				return column.getValue();
		}
		return null;
	}
	
}
