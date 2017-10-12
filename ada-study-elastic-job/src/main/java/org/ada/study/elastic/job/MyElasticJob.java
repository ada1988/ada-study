package org.ada.study.elastic.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 分片处理
 *
 */
public class MyElasticJob implements SimpleJob 
{
	@Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0: 
                // do something by sharding item 0
            	System.out.println("分片处理数据0....Thread id："+Thread.currentThread().getId());
                break;
            case 1: 
                // do something by sharding item 1
            	System.out.println("分片处理数据1....Thread id："+Thread.currentThread().getId());
                break;
            case 2: 
                // do something by sharding item 2
            	System.out.println("分片处理数据2....Thread id："+Thread.currentThread().getId());
                break;
            // case n: ...
        }
    }
}
