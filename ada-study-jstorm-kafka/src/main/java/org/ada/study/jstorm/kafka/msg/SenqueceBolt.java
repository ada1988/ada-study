package org.ada.study.jstorm.kafka.msg;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
 * Description: kafka出来的数据转换成字符串  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月23日 <br>
 *
 *  
 */

public class SenqueceBolt extends BaseBasicBolt {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SenqueceBolt.class);
	public void execute(Tuple arg0, BasicOutputCollector arg1) {
        String word = (String) arg0.getValue(0);
        String out = "output:" + word;
        LOGGER.error("SenqueceBolt 数据处理器bolt>>>>>>>>>>>>>"+out);
        //写文件
        try {
            DataOutputStream out_file = new DataOutputStream(new FileOutputStream("kafkastorm.out"));
            out_file.writeUTF(out);
            out_file.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        arg1.emit(new Values(out));
    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("message"));
    }

}
