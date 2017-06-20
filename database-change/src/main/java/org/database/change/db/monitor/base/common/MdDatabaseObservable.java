package org.database.change.db.monitor.base.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.database.change.db.monitor.base.DbLogHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.Message;

/**  
 * Filename: ProductPropertyChangeHandler.java  <br>
 *
 * Description: 数据库变更观察者  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月19日 <br>
 *
 *  
 */

public abstract class MdDatabaseObservable implements MdObservable<Message>{
	private static final Logger						logger		= LoggerFactory.getLogger( MdDatabaseObservable.class );
	protected DbLogHelper							logHelper	= new DbLogHelper( logger );
	
	@Override
	public void callBack(Message metadata) {
		if(careFor( metadata )) doWork( metadata );
	}
	/**
	 * 观察者关注的内容:数据库、表。
	 * @param metadata
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月19日
	 */
	public boolean careFor(Message metadata){
		
		String db;
		String table;
		for (Entry entry : metadata.getEntries()) {
			if (entry.getEntryType() == EntryType.ROWDATA) {
				db = entry.getHeader().getSchemaName();
				table = entry.getHeader().getTableName();
				// 过滤
				if (careForDbs().contains( db ) && careTables().contains( table ))
					return true;
			}
		}
		return false;
	}
	
	DbLogHelper dblogHelper = new DbLogHelper(logger);
	/**
	 * 回调函数，
	 * @param metadata
	 * @author: CZD  
	 * @Createtime: 2017年4月19日
	 */
	public <T> void doWork(Message metadata){
		/**
		 * 记录日志
		 */
		/*logHelper.printEntry( metadata.getEntries() );
		logHelper.printSummary( metadata, metadata.getId(), metadata.getEntries().size() );*/
		
		MdOptionsHandler<T> handler = null;
		//同一时间窗口，仅处理一次（由于定时任务存在时间，公用定时任务的时间为同一时间窗口的时间）
		Set<T> result = new HashSet<T>();
		for (Entry entry : metadata.getEntries()) {
			if (entry.getEntryType() == EntryType.ROWDATA) {
				String db = entry.getHeader().getSchemaName();
				String table = entry.getHeader().getTableName();
				if(optionHandlers().containsKey( db+"."+ table)){
					handler = (MdOptionsHandler<T>)optionHandlers().get( db+"."+ table );//强转，不太爽
					if (null != handler) 
						{
							T t = handlerWork( entry, handler );
							if(null!=t) result.add( t );
						}
					handler = null;
				}
			}
		}
		//同一时间窗口，仅处理一次（由于定时任务存在时间，公用定时任务的时间为同一时间窗口的时间）
		if(!result.isEmpty())
			timesWindowResult( Collections.unmodifiableSet( result ) );
	}
	
	/**
	 * 单表操作的处理器
	 * @param entry
	 * @param handler
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public <T> T handlerWork(Entry entry,MdOptionsHandler<T> handler){
		T t = null;
		RowChange rowChage = null;
        try {
            rowChage = RowChange.parseFrom(entry.getStoreValue());
        } catch (Exception e) {
            throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
        }
        EventType eventType = rowChage.getEventType();
        for (RowData rowData : rowChage.getRowDatasList()) {
            if (eventType == EventType.DELETE) {
            	t = handler.deleteOption( rowData );
            } else if (eventType == EventType.INSERT) {
            	t = handler.insertOption( rowData );
            } else {
            	t = handler.updateOption( rowData );
            }
        }
        return t;
	}
	
	/**
	 * 关注的数据库
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public abstract List<String> careForDbs();
	/**
	 * 关注的表
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public abstract List<String> careTables();
	
	/**
	 * 某表的处理器 （规则：db.table）
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public abstract <T> Map<String, MdOptionsHandler<T>> optionHandlers();
	
	/**
	 * 同一时间窗口，记录最终结果
	 * @param result
	 * @author: CZD  
	 * @Createtime: 2017年4月20日
	 */
	public abstract <T> void timesWindowResult(Set<T> result);
}
