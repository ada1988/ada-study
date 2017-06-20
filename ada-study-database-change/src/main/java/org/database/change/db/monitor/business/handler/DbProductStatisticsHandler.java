package org.database.change.db.monitor.business.handler;

import java.util.Arrays;

import org.database.change.db.monitor.base.common.MdOptionsHandler;
import org.database.change.db.monitor.base.common.tool.CheckChangeUtil;

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

public class DbProductStatisticsHandler implements MdOptionsHandler<String> {

	@Override
	public String deleteOption(RowData rowData) {
		for (Column column : rowData.getBeforeColumnsList()) {
			if (column.getName().equals( "pid" ))
				return column.getValue();
		}
		return null;
	}

	@Override
	public String updateOption(RowData rowData) {
		if(CheckChangeUtil.checkChange( rowData, Arrays.asList( "update_time","version" ) ))
			for (Column column : rowData.getBeforeColumnsList()) {
				if (column.getName().equals( "pid" ))
					return column.getValue();
			}
		return null;
	}

	@Override
	public String insertOption(RowData rowData) {
		for (Column column : rowData.getAfterColumnsList()) {
			if (column.getName().equals( "pid" ))
				return column.getValue();
		}
		return null;
	}

}
