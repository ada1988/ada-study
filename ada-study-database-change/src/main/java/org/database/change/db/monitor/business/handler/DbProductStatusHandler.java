package org.database.change.db.monitor.business.handler;

import org.database.change.db.monitor.base.common.MdOptionsHandler;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;

/**
 * Filename: TblProductHandler.java <br>
 *
 * Description: 产品库存表[tbl_product_statistics]变动信息处理类 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年4月20日 <br>
 *
 * 
 */

public class DbProductStatusHandler implements MdOptionsHandler<String> {

	@Override
	public String deleteOption(RowData rowData) {
		for (Column column : rowData.getBeforeColumnsList()) {
			if (column.getName().equals( "id" ))
				return column.getValue();
		}
		return null;
	}

	/**
	 * 产品状态变动，修改前后数据不同
	 */
	@Override
	public String updateOption(RowData rowData) {
		String beforeStatus = null;
		String afterStatus = null;
		String id = null;
		for (Column column : rowData.getBeforeColumnsList()) {
			if (column.getName().equals( "status" ))
			{
				beforeStatus = column.getValue();
			}
			if (column.getName().equals( "id" )){
				id = column.getValue();
			}	
		}
		for (Column column : rowData.getAfterColumnsList()) {
			if (column.getName().equals( "status" ))
				afterStatus = column.getValue();
		}

		if (beforeStatus.equals( afterStatus ))
			return null;
		else
			return id;
	}

	@Override
	public String insertOption(RowData rowData) {
		for (Column column : rowData.getAfterColumnsList()) {
			if (column.getName().equals( "id" ))
				return column.getValue();
		}
		return null;
	}

}
