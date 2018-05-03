package org.ada.study.sharing.jdbc.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.InlineShardingStrategyConfiguration;

@Configuration
public class ShardingJdbcConfig {
	private static final Logger logger = Logger.getLogger(ShardingJdbcConfig.class);
	@Value("${spring.datasource.druid.urls}")
	private String urls;
	@Value("${spring.datasource.druid.usernames}")
	private String usernames;
	@Value("${spring.datasource.druid.passwords}")
	private String passwords;
	@Value("${spring.datasource.druid.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.druid.initial-size}")
	private int initialSize;
	@Value("${spring.datasource.druid.min-idle}")
	private int minIdle;
	@Value("${spring.datasource.druid.max-active}")
	private int maxActive;
	@Value("${spring.datasource.druid.max-wait}")
	private int maxWait;
	@Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
	private int timeBetweenEvictionRunsMillis;
	@Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
	private int minEvictableIdleTimeMillis;
	@Value("${spring.datasource.druid.validation-query}")
	private String validationQuery;
	@Value("${spring.datasource.druid.test-while-idle}")
	private boolean testWhileIdle;
	@Value("${spring.datasource.druid.test-on-borrow}")
	private boolean testOnBorrow;
	@Value("${spring.datasource.druid.test-on-return}")
	private boolean testOnReturn;
	@Value("${spring.datasource.druid.pool-prepared-statements}")
	private boolean poolPerparedStatements;
	@Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
	private int maxPoolPreparedStatementPerConnectionSize;
	@Value("${spring.datasource.druid.filters}")
	private String filters;
	@Value("${spring.datasource.druid.stat-view-servlet.login-username}")
	private String loginUsername;
	@Value("${spring.datasource.druid.stat-view-servlet.login-password}")
	private String loginPassword;

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", loginUsername);
		reg.addInitParameter("loginPassword", loginPassword);
		return reg;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}

	@Bean
	@ConditionalOnProperty(prefix = "config", name = "sharding-jdbc-open", havingValue = "false")
	public DataSource druidDataSource() {
		DataSource dataSource = null;
		try {
			String[] urlArr = urls.split(",");
			if (urlArr == null || urlArr.length == 0) {
				throw new Exception("config[spring.datasource.druid.urls] is null");
			}
			String[] usernameArr = usernames.split(",");
			if (usernameArr == null || usernameArr.length == 0) {
				throw new Exception("config[spring.datasource.druid.usernames] is null");
			}
			String[] passwordArr = passwords.split(",");
			if (passwordArr == null || passwordArr.length == 0) {
				throw new Exception("config[spring.datasource.druid.passwords] is null");
			}
			int len = urlArr.length;
			if (usernameArr.length != len || passwordArr.length != len) {
				throw new Exception("config[urls-usernames-passwords] is not match");
			}
			DruidDataSource ds = new DruidDataSource();
			ds.setUrl("jdbc:mysql://" + urlArr[0].trim()
					+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
			ds.setUsername(usernameArr[0].trim());
			ds.setPassword(passwordArr[0].trim());
			ds.setDriverClassName(driverClassName);
			ds.setInitialSize(initialSize);
			ds.setMinIdle(minIdle);
			ds.setMaxActive(maxActive);
			ds.setMaxWait(maxWait);
			ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			ds.setValidationQuery(validationQuery);
			ds.setTestWhileIdle(testWhileIdle);
			ds.setTestOnBorrow(testOnBorrow);
			ds.setTestOnReturn(testOnReturn);
			ds.setPoolPreparedStatements(poolPerparedStatements);
			ds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
			ds.setFilters(filters);
			return ds;
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataSource;
	}

	@Bean
	@ConditionalOnProperty(prefix = "config", name = "sharding-jdbc-open", havingValue = "true")
	public DataSource shardingJdbcDataSource() {
		DataSource dataSource = null;
		try {
			String[] urlArr = urls.split(",");
			if (urlArr == null || urlArr.length == 0) {
				throw new Exception("config[spring.datasource.druid.urls] is null");
			}
			String[] usernameArr = usernames.split(",");
			if (usernameArr == null || usernameArr.length == 0) {
				throw new Exception("config[spring.datasource.druid.usernames] is null");
			}
			String[] passwordArr = passwords.split(",");
			if (passwordArr == null || passwordArr.length == 0) {
				throw new Exception("config[spring.datasource.druid.passwords] is null");
			}
			int len = urlArr.length;
			if (usernameArr.length != len || passwordArr.length != len) {
				throw new Exception("config[urls-usernames-passwords] is not match");
			}
			// 配置真实数据源
			Map<String, DataSource> dataSourceMap = new HashMap<>();
			DruidDataSource ds = null;
			for (int i = 0; i < len; i++) {
				ds = new DruidDataSource();
				ds.setUrl("jdbc:mysql://" + urlArr[i].trim()
						+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
				ds.setUsername(usernameArr[i].trim());
				ds.setPassword(passwordArr[i].trim());
				ds.setDriverClassName(driverClassName);
				ds.setInitialSize(initialSize);
				ds.setMinIdle(minIdle);
				ds.setMaxActive(maxActive);
				ds.setMaxWait(maxWait);
				ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
				ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
				ds.setValidationQuery(validationQuery);
				ds.setTestWhileIdle(testWhileIdle);
				ds.setTestOnBorrow(testOnBorrow);
				ds.setTestOnReturn(testOnReturn);
				ds.setPoolPreparedStatements(poolPerparedStatements);
				ds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
				ds.setFilters(filters);
				dataSourceMap.put("ds_" + i, ds);
			}

			// 配置order_form表规则
			TableRuleConfiguration cfgOrderForm = new TableRuleConfiguration();
			cfgOrderForm.setLogicTable("t_order_form");
			cfgOrderForm.setActualDataNodes("ds_0.t_order_form_${0..3}");
			cfgOrderForm.setTableShardingStrategyConfig(
					new InlineShardingStrategyConfiguration("order_no", "t_order_form_${order_no % 4}"));

			// 配置order_nurse表规则
			TableRuleConfiguration cfgOrderDetail = new TableRuleConfiguration();
			cfgOrderDetail.setLogicTable("tbl_order_detail");
			cfgOrderDetail.setActualDataNodes("ds_0.tbl_order_detail_${0..3}");
			cfgOrderDetail.setTableShardingStrategyConfig(
					new InlineShardingStrategyConfiguration("order_no", "tbl_order_detail_${order_no % 4}"));

			
			// 配置分片规则
			ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
			// 默认数据源，不分片的表在此数据源
			shardingRuleConfig.setDefaultDataSourceName("ds_0");
			shardingRuleConfig.getTableRuleConfigs().add(cfgOrderForm);
			shardingRuleConfig.getTableRuleConfigs().add(cfgOrderDetail);
			shardingRuleConfig.getBindingTableGroups().add("t_order_form, tbl_order_detail");//未绑定，会出现多条空数据
			// 获取数据源对象
			dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig,
					new ConcurrentHashMap<>(), new Properties());
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataSource;
	}
}
