package org.database.change.jms.back;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**  
 * Filename: ConfirmCallbackImpl.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月21日 <br>
 *
 *  
 */
@Component("confirmCallback")
public class ConfirmCallbackImpl implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            System.out.println("----------OK-");
        }else {
            System.out.println("------Not OK-" + cause);
        }
    }
}
