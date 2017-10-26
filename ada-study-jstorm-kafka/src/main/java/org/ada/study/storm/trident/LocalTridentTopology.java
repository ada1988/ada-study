package org.ada.study.storm.trident;

import java.util.Properties;
import java.util.UUID;

import org.ada.study.jstorm.kafka.msg.FormatMessageScheme;
import org.ada.study.storm.trident.bolt.ProductBolt;
import org.ada.study.storm.trident.filter.ProductFilter;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.trident.OpaqueTridentKafkaSpout;
import org.apache.storm.kafka.trident.TridentKafkaConfig;
import org.apache.storm.kafka.trident.TridentKafkaStateFactory;
import org.apache.storm.kafka.trident.TridentKafkaUpdater;
import org.apache.storm.kafka.trident.mapper.FieldNameBasedTupleToKafkaMapper;
import org.apache.storm.kafka.trident.selector.DefaultTopicSelector;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.tuple.Fields;

/**
 */
public class LocalTridentTopology {
	static String	zookeeperServers	= "192.168.4.82:2181,192.168.4.88:2181,192.168.4.91:2181";
	static String	kafkaServers		= "192.168.4.91:9092,192.168.4.92:9092,192.168.4.92:9092";
	static String	fromTopicName		= "nginx-log";												// 数据源
	static String	toTopicName			= "nginx-product-recommon-log";							// 目的地

	/**
	 * 创建OpaqueTridentKafkaSpout （kafka的数据源）
	 * 
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年10月26日
	 */
	public OpaqueTridentKafkaSpout createOpaqueTridentKafkaSpout() {
		// 通过zookeeper集群或单机的方式,获取kafka的Broker信息
		BrokerHosts brokerHosts = new ZkHosts( zookeeperServers );
		// 定义一个 spoutConfig（数据源配置），订阅kafka的topic为nginx-log，以/为根节点。
		TridentKafkaConfig spoutConf = new TridentKafkaConfig(brokerHosts,fromTopicName);
		// 数据源中流数据的前置处理，序列化以及自定义字段
		spoutConf.scheme = new SchemeAsMultiScheme( new FormatMessageScheme() );
		// 定义数据源
		 OpaqueTridentKafkaSpout kafkaSpout = new OpaqueTridentKafkaSpout(spoutConf);  
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
		props.put( "topic", toTopicName );
		return props;
	}

	public static void main(String[] args) throws AuthorizationException {
		LocalTridentTopology test = new LocalTridentTopology();
		// 创建一个Topology（storm的结构，数据流向）
		TridentTopology topology = new TridentTopology();

		// 定义数据源，以及数据流，并过滤、格式化
		Stream stream = topology.newStream( "nginx-log-kafka-spout", test.createOpaqueTridentKafkaSpout() )
				.each( new Fields("message"), new ProductFilter( new String[] { "*product*" } ) )
				.each( new Fields( "message" ), new ProductBolt(), new Fields( "msg" ) );

		// 定义数据流向到kafka
		TridentKafkaStateFactory stateFactory = new TridentKafkaStateFactory().withProducerProperties( test.kafkaProviderProperties() )
				.withKafkaTopicSelector( new DefaultTopicSelector( toTopicName ) )
				.withTridentTupleToKafkaMapper( new FieldNameBasedTupleToKafkaMapper<String, String>( "key", "msg" ) );

		stream.partitionPersist( stateFactory, new Fields( "msg" ), new TridentKafkaUpdater(), new Fields() );

		// 本地模式运行
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology( "lc-nginx-log-topology", new Config(), topology.build() );
		try {
			Thread.currentThread().sleep( 1000000 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cluster.killTopology( "nginx-log-topology" );
		cluster.shutdown();
		/*
		 * try { StormSubmitter.submitTopology("kafkaTridentTest", conf,
		 * topology.build()); } catch (AlreadyAliveException e) {
		 * e.printStackTrace(); } catch (InvalidTopologyException e) {
		 * e.printStackTrace(); }
		 */
	}
}
