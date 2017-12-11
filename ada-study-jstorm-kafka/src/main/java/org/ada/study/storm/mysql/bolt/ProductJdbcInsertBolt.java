package org.ada.study.storm.mysql.bolt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ada.study.storm.mysql.cover.UserMobileUrlCover;
import org.ada.study.storm.mysql.em.ProductFiledsDbEM;
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
 * Filename: ProductJdbcInsertBolt.java  <br>
 *
 * Description: 将nginx日志存储到tbl_product_log_all  <br>
 * 
 * 		
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public class ProductJdbcInsertBolt extends AbstractJdbcBolt{
	private static final Logger	LOGGER				= LoggerFactory.getLogger( ProductJdbcInsertBolt.class );
	private List<UrlHandlerMapping> patternHandlerMapping = null;
	
	public ProductJdbcInsertBolt(ConnectionProvider connectionProvider,  JdbcMapper jdbcMapper,List<UrlHandlerMapping> patternHandlerMapping) {
        super(connectionProvider);
        Validate.notNull(jdbcMapper);
        this.patternHandlerMapping = patternHandlerMapping;
    }

    private String tableName;
    private String insertQuery;

    public ProductJdbcInsertBolt withTableName(String tableName) {
        if (insertQuery != null) {
            throw new IllegalArgumentException("You can not specify both insertQuery and tableName.");
        }
        this.tableName = tableName;
        return this;
    }

    public ProductJdbcInsertBolt withInsertQuery(String insertQuery) {
        if (this.tableName != null) {
            throw new IllegalArgumentException("You can not specify both insertQuery and tableName.");
        }
        this.insertQuery = insertQuery;
        return this;
    }

    public ProductJdbcInsertBolt withQueryTimeoutSecs(int queryTimeoutSecs) {
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

    private UserMobileUrlCover userMobileUrlCover = new UserMobileUrlCover();
    
    @Override
    public void execute(Tuple tuple) {
    	Object sentence = tuple.getValue( ProductFiledsFlowEM.req_url.getPreIndex() );//获取url
		LOGGER.error( "ProductJdbcInsertBolt 数据处理前:{}", sentence );
		try {
			String[] regs;
			IUrlHandler handler = null;
			List<Column> columns = null;
			//过滤需要的url
			for(UrlHandlerMapping urlHandlerMapping:patternHandlerMapping){
				regs = urlHandlerMapping.getPatterns();
				for(String reg:regs){
					Pattern pattern = Pattern.compile(reg);
					Matcher matcher = pattern.matcher(null!=sentence?sentence.toString().trim():""); // 操作的字符串
					if(matcher.find()){
						LOGGER.error("pass--------------->ProductJdbcInsertBolt过滤器，通过该条数据:{}",sentence);
						handler = urlHandlerMapping.getUrlHandler();
						if(null!=handler){
							//保存到数据库
							insertDb((List<Column>)handler.handler( tuple ));
						}else{
							// 发射的时候直接发消息，不需要发送原来的tuple
							collector.emit( new Values( getMobile( tuple ) ) );
						}
					}
				}
			}
			LOGGER.info("discard--------------->ProductJdbcInsertBolt过滤器，未通过该条数据:{}",sentence);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 所有数据，统统进行回复
			collector.ack( tuple );
		}
    	
    	
    }
    
    /**
     * 获取用户手机号
     * @param tuple
     * @return
     * @author: CZD  
     * @Createtime: 2017年10月31日
     */
    public String getMobile(Tuple tuple){
    	int[] preIndexs = ProductFiledsDbEM.user_mobile.getPreIndexs();
		String original = null;
		for (int preIndex : preIndexs) {
			if (null == original)
				original = tuple.getString( preIndex );
			else
				original = original + "###" + tuple.getString( preIndex );
		}
		return userMobileUrlCover.valueCover( original );
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
		outputFieldsDeclarer.declare( new Fields("mobile") );
    }
}
