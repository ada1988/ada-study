package org.database.change.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

/**  
 * Filename: AmqpConfig.java  <br>
 *
 * Description:  rabbitMQ配置 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年4月21日 <br>
 *  
 */
@Configuration
public class AmqpConfig {
		@Value("${spring.rabbitmq.username}")
		private String	username;
		@Value("${spring.rabbitmq.password}")
		private String	password;
		@Value("${spring.rabbitmq.virtual-host}")
		private String	virtualHost;
		@Value("${spring.rabbitmq.host}")
		private String	host;
		@Value("${spring.rabbitmq.channel-cache-size}")
		private Integer	channelCacheSize;
		@Value("${spring.rabbitmq.publisher-confirms}")
		private Boolean	publisherConfirms;
		@Bean
		public ConnectionFactory connectionFactory() {
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
			connectionFactory.setHost( host );
			connectionFactory.setUsername( username );
			connectionFactory.setPassword( password );
			connectionFactory.setVirtualHost( virtualHost );
			connectionFactory.setChannelCacheSize( channelCacheSize );
			connectionFactory.setPublisherConfirms( publisherConfirms );
			/** 如果要进行消息回调，则这里必须要设置为true */
			return connectionFactory;
		}

		@Autowired
		@Qualifier("confirmCallback")
		private ConfirmCallback confirmCallback;
		@Autowired
		@Qualifier("recoveryCallback")
		private RecoveryCallback recoveryCallback;
		
		@Bean(name="amqpTemplate")
		/** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
		@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
		public RabbitTemplate rabbitTemplate() {
			RabbitTemplate template = new RabbitTemplate( connectionFactory() );
			template.setMessageConverter( new Jackson2JsonMessageConverter() );
			template.setRetryTemplate( retryTemplate() );
			template.setConfirmCallback( confirmCallback );
			template.setRecoveryCallback( (RecoveryCallback<?>) recoveryCallback );
			return template;
		}
		
		
		@Value("${spring.rabbitmq.initialInterval}")
		private Integer initialInterval;
		@Value("${spring.rabbitmq.multiplier}")
		private Double multiplier;
		@Value("${spring.rabbitmq.maxInterval}")
		private Integer maxInterval;
		
		@Bean(name="retryTemplate")
		public RetryTemplate retryTemplate(){
			RetryTemplate retry = new RetryTemplate();
			
			ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
			backOffPolicy.setInitialInterval( 10 );
			backOffPolicy.setMultiplier( multiplier );
			backOffPolicy.setMaxInterval( maxInterval );
			
			retry.setBackOffPolicy( backOffPolicy );
			
			return retry;
		}
		
}
