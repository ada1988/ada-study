package org.database.change.db.monitor.base.common;

import com.alibaba.otter.canal.protocol.CanalEntry.RowData;

/**  
 * Filename: MdOptionsHandler.java  <br>
 *
 * Description:  增、删、改数据的处理器 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月20日 <br>
 *
 *  
 */

public interface MdOptionsHandler<T> {
	
	/**
	 * 删除操作时的业务（删除前的数据）
	 * @param rowData
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public T deleteOption(RowData rowData);
	
	/**
	 * 更新操作时的业务（更新前的数据）
	 * @param rowData
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public T updateOption(RowData rowData);
	
	/**
	 * 插入操作时的业务（插入后的数据）
	 * @param rowData
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public T insertOption(RowData rowData);
}
