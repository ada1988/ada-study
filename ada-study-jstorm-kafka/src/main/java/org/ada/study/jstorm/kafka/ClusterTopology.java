package org.ada.study.jstorm.kafka;

import java.util.Properties;
import java.util.UUID;

import org.ada.study.jstorm.kafka.msg.FormatMessageScheme;
import org.ada.study.jstorm.kafka.msg.UrlPatternFilterBolt;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.kafka.bolt.selector.DefaultTopicSelector;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

/**
 * 通过StormTopology将该服务，注册到storm中
 * 
 * bin/storm jar
 * extlib/ada-study-jstorm-kafka-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 * org.ada.study.jstorm.kafka.ClusterTopology
 *
 */
public class ClusterTopology {
	static String	zookeeperServers	= "192.168.4.82:2181,192.168.4.88:2181,192.168.4.91:2181";
	static String	kafkaServers		= "192.168.4.91:9092,192.168.4.92:9092,192.168.4.92:9092";
	static String	fromTopicName		= "nginx-log";	// 数据源
	static String   toTopicName 		= "nginx-product-recommon-log";//目的地

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
		spoutConfig.scheme = new SchemeAsMultiScheme( new FormatMessageScheme() );
		// 定义数据源
		KafkaSpout kafkaSpout = new KafkaSpout( spoutConfig );
		return kafkaSpout;
	}

	/**
	 * 创建kafka生产者属性
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年10月26日
	 */
	public Properties kafkaProviderProperties() {
		// 设置kafka生产者
		Properties props = new Properties();
		props.put( "bootstrap.servers", kafkaServers );
		props.put( "acks", "1" );
		props.put( "key.serializer", "org.apache.kafka.common.serialization.StringSerializer" );
		props.put( "value.serializer", "org.apache.kafka.common.serialization.StringSerializer" );
		props.put( "connections.max.idle.ms", "1540000" );
		props.put( "max.request.size", "1540000" );
		props.put( "receive.buffer.bytes", "304857620" );

		props.put( Config.TOPOLOGY_TRANSFER_BUFFER_SIZE, 32 );
		props.put( Config.TOPOLOGY_EXECUTOR_RECEIVE_BUFFER_SIZE, 16384 );
		props.put( Config.TOPOLOGY_EXECUTOR_SEND_BUFFER_SIZE, 16384 );
		// 配置KafkaBolt生成的topic
		props.put( "topic", "nginx-product-recommon-log" );
		return props;
	}

	public static void main(String[] args) throws AuthorizationException {
		ClusterTopology test = new ClusterTopology();
		// 创建一个Topology（storm的结构，数据流向）
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout( "kafka-spout-id", test.createKafkaSpout() );// 处理kafkaSpout中的数据，存放到队列kafka-spout-id
		builder.setBolt( "pattern-url-bolt-id", new UrlPatternFilterBolt() ).shuffleGrouping( "kafka-spout-id" );// 处理器SenqueceBolt,读取kafka-spout-id队列中的元数据，发送到队列pattern-url-bolt-id

		KafkaBolt<String, String> kafkaBolt = new KafkaBolt<String, String>();
		kafkaBolt.withProducerProperties( test.kafkaProviderProperties() ).withTopicSelector( new DefaultTopicSelector( toTopicName ) );
		builder.setBolt( "kafkabolt", kafkaBolt ).shuffleGrouping( "pattern-url-bolt-id" );// 处理器KafkaBolt,读取pattern-url-bolt-id队列中的元数据，发送到kafka

		// 本地模式运行
		//提交到集群运行
        try {
            StormSubmitter.submitTopology("jx-nginx-log-topology", new Config(), builder.createTopology());
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        }
	}
}
