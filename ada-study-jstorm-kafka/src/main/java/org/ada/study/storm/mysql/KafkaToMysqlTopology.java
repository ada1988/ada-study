package org.ada.study.storm.mysql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ada.study.storm.mysql.bolt.CountResidenceTimesBolt;
import org.ada.study.storm.mysql.bolt.NginxLogJdbcInsertBolt;
import org.ada.study.storm.mysql.bolt.NginxLogSaveBolt;
import org.ada.study.storm.mysql.bolt.ProductJdbcInsertBolt;
import org.ada.study.storm.mysql.bolt.UserRelationJdbcInsertBolt;
import org.ada.study.storm.mysql.handler.ProductUrlHandler;
import org.ada.study.storm.mysql.handler.UserRelationUrlHandler;
import org.ada.study.storm.mysql.msg.AdaMessageScheme;
import org.ada.study.storm.mysql.msg.UrlHandlerMapping;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.common.HikariCPConnectionProvider;
import org.apache.storm.jdbc.mapper.JdbcMapper;
import org.apache.storm.jdbc.mapper.SimpleJdbcMapper;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.shade.org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Filename: KafkaToMysqlTopology.java <br>
 *
 * Description: kafka 流向 mysql <br>
 * 
 * 注册到storm
 * bin/storm jar extlib/miduo-product-look-jar-with-dependencies.jar org.ada.study.storm.mysql.KafkaToMysqlTopology
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月27日 <br>
 *
 * 
 */

public class KafkaToMysqlTopology {
	public static String	splitSign			= "\\|\\|\\|";												// 分隔符
	public static int		splitNum			= 3;

	static String			zookeeperServers	= "192.168.4.82:2181,192.168.4.88:2181,192.168.4.91:2181";
	static String			fromTopicName		= "nginx-log";												// 数据源

	/**
	 * 创建kafkaSpout （kafka的数据源）
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年10月26日
	 */
	public KafkaSpout createKafkaSpout() {
		// 通过zookeeper集群或单机的方式,获取kafka的Broker信息
		BrokerHosts brokerHosts = new ZkHosts( zookeeperServers );
		// 定义一个 spoutConfig（数据源配置），订阅kafka的topic为nginx-log，以/为根节点。
		SpoutConfig spoutConfig = new SpoutConfig( brokerHosts, fromTopicName, "/" + fromTopicName, UUID.randomUUID().toString() );
		// 数据源中流数据的前置处理，序列化以及自定义字段
		spoutConfig.scheme = new SchemeAsMultiScheme( new AdaMessageScheme() );
		spoutConfig.startOffsetTime = kafka.api.OffsetRequest.LatestTime();// 从何时的offset时间开始读，默认为最旧的offset
		// 定义数据源
		KafkaSpout kafkaSpout = new KafkaSpout( spoutConfig );
		return kafkaSpout;
	}

	/**
	 * 创建mysql 连接器
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年10月27日
	 */
	public ConnectionProvider createMysqlConnectionProvider() {
		Map hikariConfigMap = Maps.newHashMap();
		hikariConfigMap.put( "dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource" );
		hikariConfigMap.put( "dataSource.url", "jdbc:mysql://192.168.4.82/nginx-log-analysis" );
		hikariConfigMap.put( "dataSource.user", "backup" );
		hikariConfigMap.put( "dataSource.password", "miduo" );
		ConnectionProvider connectionProvider = new HikariCPConnectionProvider( hikariConfigMap );
		return connectionProvider;
	}

	/**
	 * 创建 tbl_nginx_log_all 表结构对应的字段映射
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年10月27日
	 */
	public JdbcMapper createNginxLogMapper() {
		List<Column> columnSchema = Lists.newArrayList( new Column( "ip", Types.VARCHAR ), new Column( "req_url", Types.VARCHAR ), new Column(
				"req_refer", Types.VARCHAR ), new Column( "req_time", Types.VARCHAR ), new Column( "post_params", Types.VARCHAR ), new Column(
				"status", Types.VARCHAR ), new Column( "session_id_v1", Types.VARCHAR ), new Column( "session_id_v2", Types.VARCHAR ) );
		JdbcMapper jdbcMapper = new SimpleJdbcMapper( columnSchema );
		return jdbcMapper;
	}

	/**
	 * 创建 tbl_user_relation 表结构对应的字段映射
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年10月27日
	 */
	public JdbcMapper createUserRelationMapper() {
		List<Column> columnSchema = Lists.newArrayList( new Column( "session_version", Types.VARCHAR ), new Column( "session_id", Types.VARCHAR ),
				new Column( "user_mobile", Types.VARCHAR ), new Column( "user_ip", Types.VARCHAR ), new Column( "info", Types.VARCHAR ) );
		JdbcMapper jdbcMapper = new SimpleJdbcMapper( columnSchema );
		return jdbcMapper;
	}

	/**
	 * 创建 tbl_user_relation 表结构对应的字段映射
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年10月27日
	 */
	public JdbcMapper createProductMapper() {
		List<Column> columnSchema = Lists.newArrayList( new Column( "product_type", Types.VARCHAR ), new Column( "product_id", Types.VARCHAR ),
				new Column( "look_time", Types.VARCHAR ), new Column( "user_mobile", Types.VARCHAR ), new Column( "user_ip", Types.VARCHAR ), new Column( "residence_times", Types.VARCHAR ) );
		JdbcMapper jdbcMapper = new SimpleJdbcMapper( columnSchema );
		return jdbcMapper;
	}

	/**
	 * 启动方法
	 * 
	 * @author: CZD
	 * @Createtime: 2017年10月27日
	 */
	public void start() {
		// 创建一个Topology（storm的结构，数据流向）
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout( "kafka-spout-id", createKafkaSpout() );// 处理kafkaSpout中的数据，存放到队列kafka-spout-id

		builder.setBolt( "tbl-nginx-log-bolt", new NginxLogSaveBolt() ).shuffleGrouping( "kafka-spout-id" );// 处理器SenqueceBolt,读取kafka-spout-id队列中的元数据，发送到队列pattern-url-bolt-id

		// 创建mysql目标地址
		ConnectionProvider connectionProvider = createMysqlConnectionProvider();
		NginxLogJdbcInsertBolt nginxLogBolt = new NginxLogJdbcInsertBolt( connectionProvider, createNginxLogMapper() )
				.withInsertQuery("insert into tbl_nginx_log_all (ip,req_url,req_refer,req_time,post_params,status,session_id_v1,session_id_v2) values (?,?,?,?,?,?,?,?)" )
				.withQueryTimeoutSecs( 30 );
		builder.setBolt( "tbl-nginx-log", nginxLogBolt ).shuffleGrouping( "tbl-nginx-log-bolt" );
		/**
		 * URL-Handler（请求地址-处理器）
		 */
		List<UrlHandlerMapping> relationPatternHandlerMapping = new ArrayList<UrlHandlerMapping>();
		relationPatternHandlerMapping.add( new UrlHandlerMapping( new String[] { "/public/account/login.do" }, new UserRelationUrlHandler() ) );
		relationPatternHandlerMapping.add( new UrlHandlerMapping( new String[] { "/passport/app/toAppSign.do" }, new UserRelationUrlHandler() ) );
		relationPatternHandlerMapping.add( new UrlHandlerMapping( new String[] { "/app/product/detail/1/v4_1.json", "/app/product/detail/1/v4.json",
				"/ucenter/insure/partnerOrderUrl.json"// 保险产品地址
				, "/app/product/detail/0/v4_1.json"// 投资产品地址
				, "/app/fund/detailSlow.json","/product/fund/app/detailSlow.json" }, null ) );// 基金详情某一接口
		// 过滤产品、登录url记录，存储tbl_user_relation关系，将产品url传递到下一节点处理
		UserRelationJdbcInsertBolt userRelationBolt = new UserRelationJdbcInsertBolt( connectionProvider, createUserRelationMapper(),
				relationPatternHandlerMapping ).withInsertQuery(
				"insert into tbl_user_relation_all (user_ip,session_id,user_mobile,session_version,info) values (?,?,?,?,?)" ).withQueryTimeoutSecs(
				30 );
		builder.setBolt( "tbl_user_relation", userRelationBolt ).shuffleGrouping( "tbl-nginx-log" );

		/**
		 * URL-Handler（请求地址-处理器）
		 */
		List<UrlHandlerMapping> productPatternHandlerMapping = new ArrayList<UrlHandlerMapping>();
		productPatternHandlerMapping.add( new UrlHandlerMapping( 
				new String[] { "/app/product/detail/1/v4_1.json", "/app/product/detail/1/v4.json",
				"/ucenter/insure/partnerOrderUrl.json" }, 
				new ProductUrlHandler( "1", "productId" ) ) );
		productPatternHandlerMapping.add( new UrlHandlerMapping( 
				new String[] { "/app/product/detail/0/v4_1.json" }, 
				new ProductUrlHandler( "0",	"productId" ) ) );
		productPatternHandlerMapping.add( new UrlHandlerMapping( 
				new String[] { "/product/fund/app/detailSlow.json" }, 
				new ProductUrlHandler( "4", "fundid" ) ) );
		productPatternHandlerMapping.add( new UrlHandlerMapping( 
				new String[] { "/app/product/detail/residenceTimes.json" }, 
				null ) );
		
		
		// 过滤产品url记录，存储tbl_user_relation关系
		ProductJdbcInsertBolt productBolt = new ProductJdbcInsertBolt( connectionProvider, createProductMapper(), productPatternHandlerMapping )
				.withInsertQuery( "insert into tbl_product_log_all (product_type,product_id,look_time,user_mobile,user_ip,residence_times) values (?,?,?,?,?,?)" )
				.withQueryTimeoutSecs( 30 );
		builder.setBolt( "tbl_product_log", productBolt ).shuffleGrouping( "tbl_user_relation" );// 处理器SenqueceBolt,读取kafka-spout-id队列中的元数据，发送到队列url-filter-bolt

		/**
		 * 统计停留时间
		 */
		CountResidenceTimesBolt countResidenceTimesBolt = new CountResidenceTimesBolt( connectionProvider );
		builder.setBolt( "countResidenceTimesBolt", countResidenceTimesBolt ).shuffleGrouping( "tbl_product_log" );// 处理器SenqueceBolt,读取kafka-spout-id队列中的元数据，发送到队列url-filter-bolt

		
		
		
		
		clustor( builder );
		 //local(builder);
	}

	public void local(TopologyBuilder builder) {
		// 本地模式运行
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology( "lc-nginx-log-topology", new Config(), builder.createTopology() );
		try {
			Thread.currentThread().sleep( 1000000 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cluster.killTopology( "nginx-log-topology" );
		cluster.shutdown();
	}

	public void clustor(TopologyBuilder builder) {
		// 提交到集群运行
		try {
			StormSubmitter.submitTopology( "jx-nginx-log-topology", new Config(), builder.createTopology() );
		} catch (AlreadyAliveException e) {
			e.printStackTrace();
		} catch (InvalidTopologyException e) {
			e.printStackTrace();
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		KafkaToMysqlTopology test = new KafkaToMysqlTopology();
		test.start();
	}
}
