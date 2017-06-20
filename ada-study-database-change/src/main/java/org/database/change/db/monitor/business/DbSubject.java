package org.database.change.db.monitor.business;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.database.change.db.monitor.base.DbLogHelper;
import org.database.change.db.monitor.base.common.MdAbstractSubject;
import org.database.change.db.monitor.base.thread.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;

/**
 * Filename: DbSubject.java <br>
 *
 * Description: 数据库变动(主题) <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年4月19日 <br>
 *
 * 
 */

public class DbSubject extends MdAbstractSubject<Message> {

	private static final Logger			logger						= LoggerFactory.getLogger( DbSubject.class );
	// 定时任务：用于处理，同一时间窗口，某一产品多次变动，仅记录一次。
	private ScheduledExecutorService	scheduledExecutorService	= Executors.newScheduledThreadPool( 1, new NamedThreadFactory( "DbChangeMonitor",
																			true ) );
	private ScheduledFuture<?>			baseFuture					= null;

	protected CanalConnector			connector;

	protected String					destination;

	protected long						intervalTimes;

	protected int						batchSize					= 5 * 1024;

	protected DbLogHelper				logHelper					= new DbLogHelper( logger );

	public DbSubject(CanalConnector connector, String destination, long intervalTimes) {
		this.connector = connector;
		this.destination = destination;
		this.intervalTimes = intervalTimes;
	}

	/**
	 * 启动
	 * 
	 * @author: CZD
	 * @Createtime: 2017年4月20日
	 */
	public void start() {
		logger.info( "canal客户端启动中.........................." );
		// 初始化canal连接
		try {
			this.connector.connect();
			this.connector.subscribe();
		} catch (CanalClientException e) {
			// 失败重试机制
			e.printStackTrace();
			while (true) {
				try {
					Thread.sleep( 3000 );
					this.connector.connect();
					this.connector.subscribe();
				} catch (CanalClientException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		// 开启定时器
		baseFuture = scheduledExecutorService.scheduleWithFixedDelay( new Runnable() {
			public void run() {
				logger.info( "DbChangeMonitor监听中.........................." );
				// 记录变更
				try {
					doWork();
				} catch (Throwable t) { // 防御性容错
					logger.error( "Unexpected error occur at send message, cause: " + t.getMessage(), t );
				}
			}
		}, intervalTimes, intervalTimes, TimeUnit.MILLISECONDS );
	}

	@Override
	public void subjectChange(Message metadata) {
		notifies( metadata );
	}

	public void doWork() {
		Message message = connector.getWithoutAck( batchSize ); // 获取指定数量的数据
		int size = 0;
		long batchId = -1;
		if (null != message) {
			size = message.getEntries().size();
			batchId = message.getId();
		}
		// 日志打印
		/*
		 * logHelper.printSummary(message, batchId, size);
		 * logHelper.printEntry(message.getEntries());
		 */
		if (batchId != -1 && size != 0) {
			subjectChange( message );
		}
		connector.ack( batchId ); // 提交确认
	}

	public void destroy() {
		try {
			baseFuture.cancel( true );
			connector.disconnect();
			logger.info( "DbChangeMonitor销毁.........................." );
		} catch (Throwable t) {
			logger.error( "Unexpected error occur at cancel sender timer, cause: " + t.getMessage(), t );
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		destroy();
	}
}
