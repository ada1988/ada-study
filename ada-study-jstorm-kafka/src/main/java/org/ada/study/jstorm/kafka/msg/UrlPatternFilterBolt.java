package org.ada.study.jstorm.kafka.msg;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: SenqueceBolt.java  <br>
 *
 * Description: 过滤URL数据 <br>
 * 
 * BaseBasicBolt 不可靠消息 collector自动调用ack\fail，进行应答
 * IRichBolt     可靠、可控消息   硬编码做应答ack\fail
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月23日 <br>
 *
 *  
 */

public class UrlPatternFilterBolt extends BaseBasicBolt {
	/**
	 *  序列号
	 */
	private static final long	serialVersionUID	= 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlPatternFilterBolt.class);
	
	/**
	 * tuple 数据结构
	 * collector 收集器
	 * 
	 * collector作用：回复收集器，是否正确处理
	 */
	public void execute(Tuple tuple, BasicOutputCollector collector) {
        String sentence = (String) tuple.getValue(0);
        LOGGER.error("UrlPatternFilterBolt 数据处理前:{}",sentence);
        try {
			 //发射的时候直接发消息，不需要发送原来的tuple  
			collector.emit(new Values(sentence));  
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }


    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("message"));
    }

}
