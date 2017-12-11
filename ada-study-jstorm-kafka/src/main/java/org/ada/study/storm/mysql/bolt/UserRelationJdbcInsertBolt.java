package org.ada.study.storm.mysql.bolt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ada.study.storm.mysql.em.LogFieldRalationFlowEM;
import org.ada.study.storm.mysql.em.ProductFiledsFlowEM;
import org.ada.study.storm.mysql.handler.IUrlHandler;
import org.ada.study.storm.mysql.msg.UrlHandlerMapping;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.apache.storm.jdbc.bolt.AbstractJdbcBolt;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.mapper.JdbcMapper;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: UserRelationJdbcInsertBolt.java  <br>
 *
 * Description: 处理:URL过滤以及存储用户关系 <br>
 * 
 * 过滤：产品URL、登录接口
 * 		登录接口处理：记录用户手机号与session_id、ip的关系，并存储到tbl_user_relation，丢弃处理的数据。
 * 		产品URL处理：对上一步的数据，简单过滤几个字段，传递到下一节点处理。
 * 		为拦截的URL：丢弃本次分析，无价值的信息。
 * 		
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public class UserRelationJdbcInsertBolt extends AbstractJdbcBolt{
	private static final Logger	LOGGER				= LoggerFactory.getLogger( UserRelationJdbcInsertBolt.class );
	private List<UrlHandlerMapping> patternHandlerMapping = null;
	
	public UserRelationJdbcInsertBolt(ConnectionProvider connectionProvider,  JdbcMapper jdbcMapper,List<UrlHandlerMapping> patternHandlerMapping) {
        super(connectionProvider);
        Validate.notNull(jdbcMapper);
        this.patternHandlerMapping = patternHandlerMapping;
    }

    private String tableName;
    private String insertQuery;

    public UserRelationJdbcInsertBolt withTableName(String tableName) {
        if (insertQuery != null) {
            throw new IllegalArgumentException("You can not specify both insertQuery and tableName.");
        }
        this.tableName = tableName;
        return this;
    }

    public UserRelationJdbcInsertBolt withInsertQuery(String insertQuery) {
        if (this.tableName != null) {
            throw new IllegalArgumentException("You can not specify both insertQuery and tableName.");
        }
        this.insertQuery = insertQuery;
        return this;
    }

    public UserRelationJdbcInsertBolt withQueryTimeoutSecs(int queryTimeoutSecs) {
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
    	Object sentence = tuple.getValue( LogFieldRalationFlowEM.LOG_request_uri.getLogIndex() );//获取url
		LOGGER.error( "UserRelationJdbcInsertBolt 数据处理前:{}", sentence );
		try {
			String[] regs;
			IUrlHandler handler = null;
			
			//过滤需要的url
			for(UrlHandlerMapping urlHandlerMapping:patternHandlerMapping){
				regs = urlHandlerMapping.getPatterns();
				for(String reg:regs){
					String[] nextTuple = new String[ProductFiledsFlowEM.values().length];
					Pattern pattern = Pattern.compile(reg);
					Matcher matcher = pattern.matcher(null!=sentence?sentence.toString().trim():""); // 操作的字符串
					if(matcher.find()){
						LOGGER.error("pass--------------->UserRelationJdbcInsertBolt过滤器，通过该条数据:{}",sentence);
						handler = urlHandlerMapping.getUrlHandler();
						if(null!=handler){
							//保存到数据库
							insertDb((List<Column>)handler.handler( tuple ));
						}else{
							 for(ProductFiledsFlowEM productEm:ProductFiledsFlowEM.values()){
								 	nextTuple[productEm.getNextIndex()] = tuple.getString( productEm.getPreIndex() );
					         }
							// 发射的时候直接发消息，不需要发送原来的tuple
							collector.emit( new Values( nextTuple ) );
						}
					}
				}
			}
			LOGGER.info("discard--------------->UserRelationJdbcInsertBolt过滤器，未通过该条数据:{}",sentence);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 所有数据，统统进行回复
			collector.ack( tuple );
		}
    	
    	
    }
    
    /**
     * 插入数据库方法
     * @param columns
     * @author: CZD  
     * @Createtime: 2017年10月30日
     */
    public void insertDb(List<Column> columns){
        try {
            List<List<Column>> columnLists = new ArrayList<List<Column>>();
            columnLists.add(columns);
            if(!StringUtils.isBlank(tableName)) {
                this.jdbcClient.insert(this.tableName, columnLists);
            } else {
                this.jdbcClient.executeInsertQuery(this.insertQuery, columnLists);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		String[] fields = new String[ProductFiledsFlowEM.values().length];
		for(ProductFiledsFlowEM field:ProductFiledsFlowEM.values()){
			//字段列
			fields[field.getNextIndex()] = field.getFieldName();
		}
		outputFieldsDeclarer.declare( new Fields(fields) );
    }
}
