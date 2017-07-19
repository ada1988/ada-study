package org.ada.study.cache.common.cache.v3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.ada.study.cache.common.cache.v2.support.redis.SingletonJedisPool;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import redis.clients.jedis.Jedis;

/**
 * Filename: AdaMissiongCahceBase.java <br>
 *
 * Description: 缓存丢失，回源方案 <br>
 * 
 * 缓存更新方案：canal监听DB变化，通过RabbitMQ，异步通知应用更新缓存。
 * 
 * 本案例待完善，仅提供思路：未提取堆内、分布式缓存容器，如提取出来，可统一管理；并暴露更新缓存接口，可实现基于消息的动态同步数据。
 * 					 更新缓存时，未加锁等问题。
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年7月16日 <br>
 *
 * 
 */

public abstract class AdaMissiongCahceBase<Result extends IBaseKey, Param extends IBaseKey> {
	/*
	 * 可以为以下几种方案提供默认实现，以及set方法，供外部实现
	 */
	private Map<String,Result> localContainer = new ConcurrentHashMap<String,Result>();
	private Jedis jedis=SingletonJedisPool.getJedisPool().getResource();//未考虑关闭连接
	private RedisSerializer<Object> redisSerializer=new JdkSerializationRedisSerializer();
	// 本地更新方案
	private ICacheUpdate<Result>		localUpdate				= new ICacheUpdate<Result>() {
		@Override
		public Integer updateCache(Result result) {
			localContainer.putIfAbsent( result.keyString(), result );
			return 1;
		}
	};
	// 分布式更新方案
	private ICacheUpdate<Result>		distributedUpdate		= new ICacheUpdate<Result>() {

		private RedisSerializer<Object> redisSerializer=new JdkSerializationRedisSerializer();
		@Override
		public Integer updateCache(Result result) {
			byte[] key = result.keyString().getBytes();
			try {
				byte[] value = redisSerializer.serialize( result );
				if(value!=null){
					jedis.set( key, value );
					return 1;
				}else{
					return 0;
				}
			} catch (SerializationException e) {
				e.printStackTrace();
			}finally{
				if(jedis.isConnected())
					jedis.close();
			}
			return 0;
		}
	};

	// 本地缓存方案
	private IQueryCache<Result, Param>	localQueryCache			= new IQueryCache<Result, Param>() {
		
		@Override
		public Result queryData(Param param) {
			Result result = null;
			if(localContainer.containsKey( param.keyString() ))
					result = localContainer.get( param.keyString());
			return result;
		}
	};
	// 分布式缓存方案
	private IQueryCache<Result, Param>	distributedQueryCache	= new IQueryCache<Result, Param>() {
		
		public Result queryData(Param param) {
			Result result = null;
			try {
				byte[] bys = jedis.get( param.keyString().getBytes() );
				if(null!= bys && bys.length > 0)
					result = (Result)redisSerializer.deserialize( bys );
			} catch (SerializationException e) {
				e.printStackTrace();
			}finally{
				if(jedis.isConnected())
					jedis.close();
			}
			return result;
		}
	};;

	/**
	 * 本地缓存丢失，回源方案Redis，同时更新本地缓存
	 * 
	 * @param param
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年7月16日
	 */
	private Result localMissingCache(Param param) {
		Result result = localQueryCache.queryData( param );
		System.out.println( "获取分布式缓存成功：" + result.toString() );
		if (result != null) {
			int count = localUpdate.updateCache( result );
			System.out.println( "更新本地缓存成功：" + count );
		}
		return result;
	}

	/**
	 * 分布式缓存数据丢失，回源方案DB，同时更新分布式、本地缓存
	 * 
	 * @param param
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年7月16日
	 */
	private Result distributedMissingCache(Param param) {
		Result result = distributedQueryCache.queryData( param );
		System.out.println( "获取分布式缓存成功：" + result.toString() );
		if (null != result) {
			int count = distributedUpdate.updateCache( result );
			System.out.println( "更新分布式缓存成功：" + count );
			count = localUpdate.updateCache( result );
			System.out.println( "更新本地缓存成功：" + count );
		}
		return result;
	}

	/**
	 * 获取缓存数据
	 * 
	 * @param param
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年7月16日
	 */
	public Result cacheData(Param param) {
		Result result = localQueryCache.queryData( param );
		if (null == result)
			result = localMissingCache( param );
		if (null == result) {
			result = distributedQueryCache.queryData( param );
			result = distributedMissingCache( param );
		}
		return result;
	}

	/**
	 * 获取DB数据
	 * 
	 * @param param
	 * @return
	 * @author: CZD
	 * @Createtime: 2017年7月16日
	 */
	public abstract Result queryDbData(Param param);
}
