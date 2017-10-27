package org.ada.study.storm.mysql.bolt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ada.study.storm.mysql.em.LogFieldRalationEM;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.apache.storm.jdbc.bolt.AbstractJdbcBolt;
import org.apache.storm.jdbc.bolt.JdbcInsertBolt;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.mapper.JdbcMapper;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: AdaJdbcInsertBolt.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public class AdaJdbcInsertBolt extends AbstractJdbcBolt{
	public AdaJdbcInsertBolt(ConnectionProvider connectionProvider,  JdbcMapper jdbcMapper) {
        super(connectionProvider);
        Validate.notNull(jdbcMapper);
        this.jdbcMapper = jdbcMapper;
    }

	private static final Logger LOG = LoggerFactory.getLogger(AdaJdbcInsertBolt.class);

    private String tableName;
    private String insertQuery;
    private JdbcMapper jdbcMapper;

    public AdaJdbcInsertBolt withTableName(String tableName) {
        if (insertQuery != null) {
            throw new IllegalArgumentException("You can not specify both insertQuery and tableName.");
        }
        this.tableName = tableName;
        return this;
    }

    public AdaJdbcInsertBolt withInsertQuery(String insertQuery) {
        if (this.tableName != null) {
            throw new IllegalArgumentException("You can not specify both insertQuery and tableName.");
        }
        this.insertQuery = insertQuery;
        return this;
    }

    public AdaJdbcInsertBolt withQueryTimeoutSecs(int queryTimeoutSecs) {
        this.queryTimeoutSecs = queryTimeoutSecs;
        return this;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        super.prepare(map, topologyContext, collector);
        if(StringUtils.isBlank(tableName) && StringUtils.isBlank(insertQuery)) {
            throw new IllegalArgumentException("You must supply either a tableName or an insert Query.");
        }
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            List<Column> columns = jdbcMapper.getColumns(tuple);
            List<List<Column>> columnLists = new ArrayList<List<Column>>();
            columnLists.add(columns);
            if(!StringUtils.isBlank(tableName)) {
                this.jdbcClient.insert(this.tableName, columnLists);
            } else {
                this.jdbcClient.executeInsertQuery(this.insertQuery, columnLists);
            }
           // this.collector.ack(tuple);
        } catch (Exception e) {
        	e.printStackTrace();
           /* this.collector.reportError(e);
            this.collector.fail(tuple);*/
        	//this.collector.ack(tuple);
        }finally {
			// 所有数据，统统进行回复
			collector.ack( tuple );
		}
    }
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		String[] fields = new String[LogFieldRalationEM.values().length];
		int num = 0;
		for(LogFieldRalationEM field:LogFieldRalationEM.values()){
			//字段列
			fields[num] = field.getFieldName();
			num = num+1;
		}
		outputFieldsDeclarer.declare( new Fields(fields) );
    }
}
