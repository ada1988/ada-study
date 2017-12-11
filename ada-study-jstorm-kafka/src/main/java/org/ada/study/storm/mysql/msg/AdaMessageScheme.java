package org.ada.study.storm.mysql.msg;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ada.study.jstorm.kafka.LocalTopology;
import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**  
 * Filename: AdaMessageScheme.java  <br>
 *
 * Description: 序列化nginx日志的数据  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public class AdaMessageScheme  implements Scheme {

	private static final long	serialVersionUID	= 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AdaMessageScheme.class);

    @Override
    public List<Object> deserialize(ByteBuffer bytes) {
        try {
        	String sentence = null;
        	 if (bytes.hasArray()) {
        		        int base = bytes.arrayOffset();
        		        sentence = new String(bytes.array(), base + bytes.position(), bytes.remaining());
        	}else{
        		sentence = new String(Utils.toByteArray(bytes), Charset.forName("UTF-8"));
        	}
        	Map<String,Object> fieldValues = splitDataToJSONString( sentence );
        	
        	String msg = JSON.toJSONString( fieldValues );
        	String url = fieldValues.get("field-"+LocalTopology.splitNum).toString();
        	LOGGER.debug("对kafka数据，进行序列化、格式化={}    url={}",msg,url);
            return new Values(msg.trim(),url.trim());
        } catch (Exception e) {
            LOGGER.error("对kafka数据，进行序列化、格式化 Cannot parse the provided message!");
        }
        LOGGER.error("对kafka数据，进行序列化、格式化={}    url={}","错误数据","");
        return new Values("错误数据","");
    }

    /**
     * 格式化数据格式为Map(key-value)，后期方便使用
     * @param sentence
     * @return
     * @author: CZD  
     * @Createtime: 2017年10月26日
     */
    public Map<String,Object> splitDataToJSONString(String sentence){
    	Map<String,Object> values =  new HashMap<String,Object>();
    	int fieldNum = 0;
    	//通过特殊字符，将语句，转换成字段值
		for (String value : sentence.split(LocalTopology.splitSign)){
			values.put("field-"+fieldNum, value );
			fieldNum = fieldNum + 1;
		}
		return values;
    }
    
    @Override
    public Fields getOutputFields() {
        return new Fields("message","url");
    }
}
