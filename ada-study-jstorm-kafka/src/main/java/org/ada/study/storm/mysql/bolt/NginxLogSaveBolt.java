package org.ada.study.storm.mysql.bolt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ada.study.storm.mysql.cover.DateTimeCover;
import org.ada.study.storm.mysql.cover.IFieldValueCovert;
import org.ada.study.storm.mysql.cover.SessionIdCovert;
import org.ada.study.storm.mysql.cover.StatusCover;
import org.ada.study.storm.mysql.em.LogFieldRalationFlowEM;
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
 * Description: 格式化nginx日志 <br>
 * 
 * 	数据结构
 * 	ip---req_url---req_refer---req_time---post_params---session_id_v1---session_id_v2---status
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
	public Map<LogFieldRalationFlowEM,IFieldValueCovert> relations = new HashMap<LogFieldRalationFlowEM, IFieldValueCovert>();
	{
		
		//字段处理器
		relations.put( LogFieldRalationFlowEM.LOG_http_cookie_session_id1, new SessionIdCovert( "_cus_sessionid" ) );
		relations.put( LogFieldRalationFlowEM.LOG_http_cookie_session_id2, new SessionIdCovert( "MDSESSION" ) );
		relations.put( LogFieldRalationFlowEM.LOG_time_local, new DateTimeCover() );
		relations.put( LogFieldRalationFlowEM.LOG_status, new StatusCover() );
		
		
		fields = new String[LogFieldRalationFlowEM.values().length];
		for(LogFieldRalationFlowEM field:LogFieldRalationFlowEM.values()){
			//字段列
			fields[field.getNextIndex()] = field.getFieldName();
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
			//获取所有属性值
			for(LogFieldRalationFlowEM relation:LogFieldRalationFlowEM.values()){
				value = json.getString( "field-"+relation.getLogIndex() );
				handler = relations.get( relation );
				if(null!=handler){
					value = handler.valueCover( value );
				}
				results.add( value.trim() );
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
