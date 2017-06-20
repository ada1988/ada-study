package org.database.change.db.monitor.business.observable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.database.change.db.monitor.base.DbLogHelper;
import org.database.change.db.monitor.base.common.MdDatabaseObservable;
import org.database.change.db.monitor.base.common.MdOptionsHandler;
import org.database.change.db.monitor.business.handler.DbProductStatisticsHandler;
import org.database.change.db.monitor.business.handler.DbProductStatusHandler;
import org.database.change.db.monitor.business.send.IMQHandler;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filename: StatisticChangeHandler.java <br>
 *
 * Description: 库存信息，觀察者 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年4月19日 <br>
 *
 * 
 */

public class StatisticChangeObservable extends MdDatabaseObservable {
	private static final Logger				logger		= LoggerFactory.getLogger( StatisticChangeObservable.class );
	protected DbLogHelper					logHelper	= new DbLogHelper( logger );
	private IMQHandler sender = null;
	/**
	 * 表操作的处理器，仅处理数据修改、删除、新增操作
	 */
	private Map<String, MdOptionsHandler<String>>	handlers	= new ConcurrentHashMap<String, MdOptionsHandler<String>>();
	{
		handlers.put( "db_product.db_product_statistics", new DbProductStatisticsHandler() );
		handlers.put( "db_product.db_product", new DbProductStatusHandler() );
	}
	/**
	 * 关心的数据库
	 */
	private List<String> dbs = Arrays.asList( "db_product" );
	/**
	 * 关心的表
	 */
	private List<String> tables = Arrays.asList( "db_product_statistics", "db_product" );
	
	public StatisticChangeObservable(IMQHandler sender){
		this.sender = sender;
	}

	@Override
	public List<String> careForDbs() {
		return dbs;
	}

	@Override
	public List<String> careTables() {
		return tables;
	}

	@Override
	public Map<String, MdOptionsHandler<String>> optionHandlers() {
		return handlers;
	}


	@Override
	public <String> void timesWindowResult(Set<String> result) {
		Iterator<String> iterator = result.iterator();
		String productId = null;
		if (iterator.hasNext()) {
			productId = iterator.next();
			logger.error( "（库存信息）变动的唯一标识（ID）："+productId );
		}
		sender.sendRabbitMessage( result );
	}

}
