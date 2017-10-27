package org.ada.study.storm.mysql.bolt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ada.study.storm.mysql.em.LogFieldRalationEM;
import org.ada.study.storm.mysql.handler.DateTimeCover;
import org.ada.study.storm.mysql.handler.IFieldValueCovert;
import org.ada.study.storm.mysql.handler.SessionIdCovert;
import org.ada.study.storm.mysql.handler.StatusCover;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Filename: NginxLogSaveBolt.java <br>
 *
 * Description: <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月27日 <br>
 *
 * 
 */

public class NginxLogSaveBolt extends BaseRichBolt {
	/**
	 * 序列号
	 */
	private static final long	serialVersionUID	= 1L;

	private static final Logger	LOGGER				= LoggerFactory.getLogger( NginxLogSaveBolt.class );

	private OutputCollector		collector			= null;

	public String[] fields = null;
	//nginx 日志字段对应的处理类
	public Map<LogFieldRalationEM,IFieldValueCovert> relations = new HashMap<LogFieldRalationEM, IFieldValueCovert>();
	{
		
		//字段处理器
		relations.put( LogFieldRalationEM.LOG_http_cookie_session_id1, new SessionIdCovert( "_cus_sessionid" ) );
		relations.put( LogFieldRalationEM.LOG_http_cookie_session_id2, new SessionIdCovert( "MDSESSION" ) );
		relations.put( LogFieldRalationEM.LOG_time_local, new DateTimeCover() );
		relations.put( LogFieldRalationEM.LOG_status, new StatusCover() );
		
		
		fields = new String[LogFieldRalationEM.values().length];
		int num = 0;
		for(LogFieldRalationEM field:LogFieldRalationEM.values()){
			//字段列
			fields[num] = field.getFieldName();
			num = num+1;
		}
	}
	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare( new Fields(fields) );
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		Object sentence = input.getValue( 0 );
		LOGGER.debug( "NginxLogSaveBolt 数据处理前:{}", sentence );
		try {
			List<String> results = new ArrayList<String>();
			JSONObject json = null;
			try {
				json = JSON.parseObject( sentence.toString() );
			} catch (Exception e) {
				LOGGER.error( "数据错误：{}" ,sentence.toString());
				return;
			}
			String value = null;
			IFieldValueCovert handler = null;
			//获取所用属性值
			for(LogFieldRalationEM relation:LogFieldRalationEM.values()){
				value = json.getString( "field-"+relation.getLogIndex() );
				handler = relations.get( relation );
				if(null!=handler){
					value = handler.valueCover( value );
				}
				results.add( value );
			}
			// 发射的时候直接发消息，不需要发送原来的tuple
			collector.emit( new Values( results.toArray() ) );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 所有数据，统统进行回复
			collector.ack( input );
		}
	}
}
