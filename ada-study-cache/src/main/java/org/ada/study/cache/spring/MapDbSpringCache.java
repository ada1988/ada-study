package org.ada.study.cache.spring;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.core.serializer.support.SerializationDelegate;
import org.springframework.util.Assert;
/**
 * /**
 * Filename: MiduoConfig.java <br>
 *
 * Description: 自定义缓存Cache<br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年6月16日 <br>
 * 
 */
public class MapDbSpringCache extends AbstractValueAdaptingCache {
	private static Logger logger = LoggerFactory.getLogger(MapDbSpringCache.class);

	//init off-heap store with 2GB size limit
	private static DB db = null;
	static{
		logger.error("MapDbSpringCache db 被初始化....");
		db = DBMaker
			    .memoryDirectDB()    //use off-heap memory, on-heap is `.memoryDB()`
			    .transactionDisable()   //better performance
			    .make();
	}
	
	private final String name;

	private final HTreeMap<Object, Object> store;

	private final SerializationDelegate serialization;
	/**
	 * Create a new ConcurrentMapCache with the specified name.
	 * @param name the name of the cache
	 */
	public MapDbSpringCache(String name) {
		 //create map, entries are expired if not accessed (get,iterate) for 10 seconds or 30 seconds after 'put'
        //There is also maximal size limit to prevent OutOfMemoryException
		this(name, db
                .hashMapCreate(name)
                .expireMaxSize(1000)
                //.expireAfterWrite(2, TimeUnit.HOURS)
                //.expireAfterAccess(2, TimeUnit.HOURS)
                .make(), true);
		logger.info("MapDbSpringCache 构造器1个参数"+name);
	}

	/**
	 * Create a new ConcurrentMapCache with the specified name.
	 * @param name the name of the cache
	 * @param allowNullValues whether to accept and convert {@code null}
	 * values for this cache
	 */
	public MapDbSpringCache(String name, boolean allowNullValues) {
		 //create map, entries are expired if not accessed (get,iterate) for 10 seconds or 30 seconds after 'put'
        //There is also maximal size limit to prevent OutOfMemoryException
		this(name, db
                .hashMapCreate(name)
                .expireMaxSize(1000)
                //.expireAfterWrite(2, TimeUnit.HOURS)
                //.expireAfterAccess(2, TimeUnit.HOURS)
                .make(), allowNullValues);
		logger.info("MapDbSpringCache 构造器2个参数"+name+","+allowNullValues);
	}

	/**
	 * Create a new ConcurrentMapCache with the specified name and the
	 * given internal {@link ConcurrentMap} to use.
	 * @param name the name of the cache
	 * @param store the ConcurrentMap to use as an internal store
	 * @param allowNullValues whether to allow {@code null} values
	 * (adapting them to an internal null holder value)
	 */
	public MapDbSpringCache(String name, HTreeMap<Object, Object> store, boolean allowNullValues) {
		this(name, store, allowNullValues, null);
		logger.info("MapDbSpringCache 构造器3个参数"+name+","+store.size());
	}

	/**
	 * Create a new ConcurrentMapCache with the specified name and the
	 * given internal {@link ConcurrentMap} to use. If the
	 * {@link SerializationDelegate} is specified,
	 * {@link #isStoreByValue() store-by-value} is enabled
	 * @param name the name of the cache
	 * @param store the ConcurrentMap to use as an internal store
	 * @param allowNullValues whether to allow {@code null} values
	 * (adapting them to an internal null holder value)
	 * @param serialization the {@link SerializationDelegate} to use
	 * to serialize cache entry or {@code null} to store the reference
	 * @since 4.3
	 */
	protected MapDbSpringCache(String name, HTreeMap<Object, Object> store,
			boolean allowNullValues, SerializationDelegate serialization) {

		super(allowNullValues);
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(store, "Store must not be null");
		this.name = name;
		this.store = store;
		this.serialization = serialization;
		logger.info("MapDbSpringCache 构造器4个参数"+name);
	}



	/**
	 * Return whether this cache stores a copy of each entry ({@code true}) or
	 * a reference ({@code false}, default). If store by value is enabled, each
	 * entry in the cache must be serializable.
	 * @since 4.3
	 */
	public final boolean isStoreByValue() {
		return (this.serialization != null);
	}
	//缓存的名字  
	@Override
	public final String getName() {
		return this.name;
	}

	//得到底层使用的缓存，如Ehcache  
	@Override
	public final ConcurrentMap<Object, Object> getNativeCache() {
		return this.store;
	}

	@Override
	protected Object lookup(Object key) {
		logger.info("MapDbSpringCache lookup:"+key);
		return this.store.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		logger.info("MapDbSpringCache get:"+key);
		if (this.store.containsKey(key)) {
			return (T) get(key).get();
		}
		else {
			synchronized (this.store) {
				if (this.store.containsKey(key)) {
					return (T) get(key).get();
				}
				T value;
				try {
					value = valueLoader.call();
				}
				catch (Throwable ex) {
					throw new ValueRetrievalException(key, valueLoader, ex);
				}
				put(key, value);
				return value;
			}
		}
	}

	@Override
	public void put(Object key, Object value) {
		logger.info("MapDbSpringCache put:"+key+","+value);
		this.store.put(key, toStoreValue(value));
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		logger.info("MapDbSpringCache putIfAbsent:"+key+","+value);
		Object existing = this.store.putIfAbsent(key, toStoreValue(value));
		return toValueWrapper(existing);
	}
	//从缓存中移除key对应的缓存  
	@Override
	public void evict(Object key) {
		logger.info("MapDbSpringCache evict:"+key);
		this.store.remove(key);
	}
	//清空缓存  
	@Override
	public void clear() {
		this.store.clear();
	}

	@Override
	protected Object toStoreValue(Object userValue) {
		Object storeValue = super.toStoreValue(userValue);
		if (this.serialization != null) {
			try {
				return serializeValue(storeValue);
			}
			catch (Throwable ex) {
				throw new IllegalArgumentException("Failed to serialize cache value '" + userValue +
						"'. Does it implement Serializable?", ex);
			}
		}
		else {
			return storeValue;
		}
	}

	private Object serializeValue(Object storeValue) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			this.serialization.serialize(storeValue, out);
			return out.toByteArray();
		}
		finally {
			out.close();
		}
	}

	@Override
	protected Object fromStoreValue(Object storeValue) {
		if (this.serialization != null) {
			try {
				return super.fromStoreValue(deserializeValue(storeValue));
			}
			catch (Throwable ex) {
				throw new IllegalArgumentException("Failed to deserialize cache value '" + storeValue + "'", ex);
			}
		}
		else {
			return super.fromStoreValue(storeValue);
		}

	}

	private Object deserializeValue(Object storeValue) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream((byte[]) storeValue);
		try {
			return this.serialization.deserialize(in);
		}
		finally {
			in.close();
		}
	}


}
