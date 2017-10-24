package org.ada.study.jstorm.kafka.msg;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: MessageScheme.java  <br>
 *
 * Description:   kafka出来的数据转换成字符串<br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月23日 <br>
 *
 *  
 */

public class MessageScheme implements Scheme {

	private static final long	serialVersionUID	= 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageScheme.class);

    @Override
    public List<Object> deserialize(ByteBuffer bytes) {
        try {
            String msg = new String(bytes.array(), "UTF-8");
            LOGGER.error("接收kafka数据，数据序列化>>>>>>>>>>>>>"+msg);
            return new Values(msg);
        } catch (Exception e) {
            LOGGER.error("Cannot parse the provided message!");
        }

        return null;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("msg");
    }

}
