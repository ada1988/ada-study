package org.ada.study.websocket.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ada.study.websocket.msg.ClientMessage;
import org.ada.study.websocket.msg.ServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**  
 * Filename: WebSocketController.java  <br>
 *
 * Description: 
 * @SendTo只能推送给所有人
 * @SendToUser只能推送给请求消息的那个人，  <br>
 * SimpMessagingTemplate有俩个推送的方法
		convertAndSend(destination, payload); //将消息广播到特定订阅路径中，类似@SendTo 
		convertAndSendToUser(user, destination, payload);//将消息推送到固定的用户订阅路径中
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年7月9日 <br>
 *
 *  
 */
@Controller
public class WebSocketController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Scheduled(fixedRate = 1000)
    public void sendToClientMsg() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/subscribeTest", new ServerMessage("服务器主动推的数据:"+df.format(new Date())));
    }
    
    @MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")
    public ServerMessage sendDemo(ClientMessage message) {
        logger.info("接收到了信息" + message.getName());
        return new ServerMessage("你发送的消息为:" + message.getName());
    }

    @SubscribeMapping("/subscribeTest")
    public ServerMessage sub() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }

}
