package org.ada.study.storm.mysql;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ada.study.jstorm.kafka.msg.UrlFilterBolt;
import org.ada.study.storm.mysql.bolt.AdaJdbcInsertBolt;
import org.ada.study.storm.mysql.bolt.NginxLogSaveBolt;
import org.ada.study.storm.mysql.msg.AdaMessageScheme;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
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
		spoutConfig.startOffsetTime = kafka.api.OffsetRequest.LatestTime();//从何时的offset时间开始读，默认为最旧的offset
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
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年10月27日
	 */
	public JdbcMapper createNginxLogMapper(){
		List<Column> columnSchema  = Lists.newArrayList(
				new Column("ip", Types.VARCHAR),
				new Column("req_url", Types.VARCHAR),
				new Column("req_refer", Types.VARCHAR),
				new Column("req_time", Types.VARCHAR),
				new Column("post_params", Types.VARCHAR),
				new Column("status", Types.VARCHAR),
				new Column("session_id_v1", Types.VARCHAR),
				new Column("session_id_v2", Types.VARCHAR));
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
		AdaJdbcInsertBolt nginxLogBolt = new AdaJdbcInsertBolt( connectionProvider, createNginxLogMapper() ).withInsertQuery(
				"insert into tbl_nginx_log_all (ip,req_url,req_refer,req_time,post_params,status,session_id_v1,session_id_v2) values (?,?,?,?,?,?,?,?)" )
				.withQueryTimeoutSecs( 30 );
		builder.setBolt( "tbl-nginx-log", nginxLogBolt ).shuffleGrouping( "tbl-nginx-log-bolt" );

		builder.setBolt( "url-filter-bolt", new UrlFilterBolt( new String[] { "https://www.miduo.com/product/list_1/5.htm" } ) ).shuffleGrouping(
				"tbl-nginx-log" );// 处理器SenqueceBolt,读取kafka-spout-id队列中的元数据，发送到队列url-filter-bolt

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
	
	
	public static void main(String[] args) {
		KafkaToMysqlTopology test = new KafkaToMysqlTopology();
		test.start();
	}
}
