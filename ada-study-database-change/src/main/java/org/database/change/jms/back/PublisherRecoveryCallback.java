package org.database.change.jms.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.stereotype.Component;


/**  
 * Filename: PublisherRecoveryCallback.java  <br>
 *
 * Description:  三次重试后仍然失败会调用次方法  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月21日 <br>
 *
 *  
 */
@Component("recoveryCallback")
public class PublisherRecoveryCallback implements RecoveryCallback{

private Logger logger = LoggerFactory.getLogger(PublisherRecoveryCallback.class);
	
    @Override
    public Object recover(RetryContext context) throws Exception {
    	if(context.getLastThrowable() != null){
    		logger.error("",context.getLastThrowable());
    	}else{
    		logger.error("no throwable exception");
    	}
        return null;
    }

}
