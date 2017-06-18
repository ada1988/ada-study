package org.ada.study.cache.spring;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
/**
 * Filename: MiduoConfig.java <br>
 *
 * Description: 自定义缓存CacheManager<br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年6月16日 <br>
 * 
 */
public class MapDbSpringCacheMannger extends AbstractCacheManager {
	private Collection<? extends Cache> caches;


	/**
	 * Specify the collection of Cache instances to use for this CacheManager.
	 */
	public void setCaches(Collection<? extends Cache> caches) {
		this.caches = caches;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		System.out.println("MapDbSpringCacheMannger-caches初始化大小："+caches.size());
		return this.caches;
	}

}
