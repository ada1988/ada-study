package org.ada.study.elastic.job;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**  
 * Filename: JobDemo.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月9日 <br>
 *
 *  
 */

public class JobDemo {
	 public static void main(String[] args) {
	        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
	    }
	    
	    private static CoordinatorRegistryCenter createRegistryCenter() {
	        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("192.168.127.128:2181", "elastic-job-demo"));
	        regCenter.init();
	        return regCenter;
	    }
	    
	    private static LiteJobConfiguration createJobConfiguration() {
	        // 创建作业配置
	    	// 定义作业核心配置
	        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 10).build();
	        // 定义SIMPLE类型配置
	        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyElasticJob.class.getCanonicalName());
	        // 定义Lite作业根配置
	        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
	        return simpleJobRootConfig;
	    }
}
