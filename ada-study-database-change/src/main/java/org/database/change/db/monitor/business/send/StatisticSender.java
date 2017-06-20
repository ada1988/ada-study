package org.database.change.db.monitor.business.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**  
 * Filename: StatisticSender.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月20日 <br>
 *
 *  
 */
@Component("statisticSender")
public class StatisticSender implements IMQHandler{
private static final Logger logger = LoggerFactory.getLogger(StatisticSender.class);
	
	@Autowired
	private RabbitTemplate amqpTemplate;
	@Value("${spring.rabbitmq.product.exchange}")
	private String exchange;
	@Value("${spring.rabbitmq.statisticSender.routingKey}")
	private String routingKey;
	@Value("${spring.rabbitmq.statisticSender.statisticQueue}")
	private String statisticQueue;
	
	@Bean
	public Queue statisticQueue() {
		return new Queue(statisticQueue);
	}
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange);//直连方式：生产者、消费者交换器名称必须一致
	}
	@Bean
	public Binding bindingStatistic(DirectExchange exchange, Queue statisticQueue) {
		return BindingBuilder.bind(statisticQueue).to(exchange).with(routingKey);
	}
	
	@Autowired
	private DirectExchange directExchange;
	
	public <T> void sendRabbitMessage(T object) {
		try {
			amqpTemplate.convertAndSend(exchange,routingKey, JSON.toJSON( object ));
		} catch (Exception e) {
			logger.error("产品变更队列",e);
		}
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
}
