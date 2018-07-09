package org.ada.study.websocket.event;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**  
 * Filename: MySessionConnectedEvent.java  <br>
 *
 * Description: 通过这个事件，我们可以把用户的登录授权信息保存起来，
 * 放到ServletContext中，以便于我们在推送消息的时候，拿到用户信息，
 * 当然，这里你也可以把用户的登录授权信息保存到其他的缓存策略中。  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年7月9日 <br>
 *
 *  
 */

public class MySessionConnectedEvent implements ApplicationListener<SessionConnectedEvent> {
	@Override
    public void onApplicationEvent(SessionConnectedEvent event) {
		
        StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage()); //获取消息头
        String name = headers.getUser().getName(); //获取账号名
        ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext(); //SpringBeanUtil的作用就是获取ServletContext 
        Object obj = sc.getAttribute("users");
        Map<String, Principal> users = (Map<String, Principal>) (obj == null ? new HashMap<String,Principal>() : obj);
        users.put(name, headers.getUser());

        sc.setAttribute("users", users); //将用户信息已map格式放存放起来

    }
}
