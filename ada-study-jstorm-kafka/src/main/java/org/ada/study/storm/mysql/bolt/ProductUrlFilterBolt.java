package org.ada.study.storm.mysql.bolt;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

/**
 * Filename: SenqueceBolt.java <br>
 *
 * Description: 过滤URL数据 <br>
 * 
 * BaseBasicBolt 不可靠消息 collector自动调用ack\fail，进行应答 IRichBolt 可靠、可控消息
 * 硬编码做应答ack\fail
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月23日 <br>
 *
 * 
 */

public class ProductUrlFilterBolt extends BaseRichBolt {
	/**
	 * 序列号
	 */
	private static final long	serialVersionUID	= 1L;

	private static final Logger	LOGGER				= LoggerFactory.getLogger( ProductUrlFilterBolt.class );

	private String[] patterns = null;
	private OutputCollector		collector			= null;
	public ProductUrlFilterBolt(String[] patterns ){
		this.patterns = patterns;
	}
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare( new Fields( "message" ) );
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		Object sentence = input.getValue( LogFieldRalationFlowEM.LOG_request_uri.getLogIndex() );//获取url
		LOGGER.error( "UrlFilterBolt 数据处理前:{}", sentence );
		try {
			//过滤需要的url
			for(String reg:patterns){
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(null!=sentence?sentence.toString().trim():""); // 操作的字符串
				if(matcher.find()){
					LOGGER.error("pass--------------->UrlFilterBolt过滤器，通过该条数据:{}",sentence);
					// 发射的时候直接发消息，不需要发送原来的tuple
					collector.emit( new Values( input.getValue( 0 ) ) );
				}
			}
			LOGGER.info("discard--------------->UrlFilterBolt过滤器，未通过该条数据:{}",sentence);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//所有数据，统统进行回复
			collector.ack( input );
		}
	}

}
