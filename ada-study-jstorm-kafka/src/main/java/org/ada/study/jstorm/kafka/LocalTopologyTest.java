package org.ada.study.jstorm.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.ada.study.jstorm.kafka.msg.MessageScheme;
import org.ada.study.jstorm.kafka.msg.SenqueceBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
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
 * 将该服务，注册到storm中
 * 
 * bin/storm jar extlib/ada-study-jstorm-kafka-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.ada.study.jstorm.kafka.TopologyTest
 *
 */
public class LocalTopologyTest 
{
    public static void main( String[] args ) throws AuthorizationException
    {
    	  String zookeeper = "192.168.4.82:2181,192.168.4.88:2181,192.168.4.91:2181";
    	  //通过zookeeper集群或单机的方式,获取kafka的Broker信息
    	  BrokerHosts brokerHosts = new ZkHosts(zookeeper);
    	  //定义一个 spoutConfig（数据源配置），订阅kafka的topic为nginx-log，以/为根节点。
          SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "nginx-log", "/", UUID.randomUUID().toString());
          //数据源中流数据的前置处理，序列化以及自定义字段
          spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());
          //定义数据源
          KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
          
          //创建一个Topology（storm的结构，数据流向）
          TopologyBuilder builder = new TopologyBuilder();
          builder.setSpout("kafka-spout-id", kafkaSpout);//处理kafkaSpout中的数据，存放到队列kafka-spout-id
          builder.setBolt("pattern-url-bolt-id", new SenqueceBolt()).shuffleGrouping("kafka-spout-id");//处理器SenqueceBolt,读取kafka-spout-id队列中的元数据，发送到队列pattern-url-bolt-id
          //设置kafka生产者
          Properties props = new Properties();
          props.put("bootstrap.servers", "192.168.4.91:9092,192.168.4.92:9092");
          props.put("acks", "1");
          props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
          props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
          KafkaBolt<String, String> kafkaBolt = new KafkaBolt<String, String>();
          kafkaBolt.withProducerProperties(props).withTopicSelector(new DefaultTopicSelector("nginx-product-recommon-log"));
          builder.setBolt("kafkabolt", kafkaBolt).shuffleGrouping("pattern-url-bolt-id");//处理器KafkaBolt,读取pattern-url-bolt-id队列中的元数据，发送到kafka
          
          /**
           * kafka 设置基本数据
           */
          Config kafkaConf = new Config();//基于生产环境的配置为主，不可随意配置
          
      	  //不输出调试信息
      	  kafkaConf.setDebug(true);
          //设置一个spout task中处于pending状态的最大的tuples数量
      	  kafkaConf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
      	  Map<String, String> map=new HashMap<String,String>();
      	  // 配置Kafka broker地址
          map.put("bootstrap.servers", "192.168.4.92:9092,192.168.4.91:9092");
          // serializer.class为消息的序列化类
          map.put("serializer.class", "kafka.serializer.StringEncoder");
          kafkaConf.put("kafka.broker.properties", map);
          // 配置KafkaBolt生成的topic
          kafkaConf.put("topic", "nginx-product-recommon-log");
        //本地模式运行
          LocalCluster cluster = new LocalCluster();
          cluster.submitTopology("lc-nginx-log-topology", kafkaConf, builder.createTopology());
          try {
			Thread.currentThread().sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          cluster.killTopology("nginx-log-topology");
          cluster.shutdown();
          
    }
}
