package org.ada.study.flow.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.miduo.common.model.Resp;
import com.miduo.common.vo.CacheInfoVo;


/**  
 * Filename: CacheController.java  <br>
 *
 * Description: spring管理的缓存  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月5日 <br>
 *
 *  
 */
@RestController
public class CacheController{
	private static final Logger LOG=LoggerFactory.getLogger(CacheController.class);
	/**
	 * 缓存管理器
	 */
	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * cache list
	 * @return
	 */
	@RequestMapping("/rpc/cache/list.do")
	public List<CacheInfoVo> cacheList(){
		List<CacheInfoVo> caches=new ArrayList<CacheInfoVo>();
		Collection<String> cacheNames=this.cacheManager.getCacheNames();
		if(cacheNames!=null){
			for(String name:cacheNames){
				Cache cache=this.cacheManager.getCache(name);
				if(cache!=null){
					caches.add(convert(cache));
				}
			}
		}
		return caches;
	}
	/**
	 * 缓存清理
	 * @param cache
	 * @param key
	 * @return
	 */
	@RequestMapping("/rpc/cache/clear.do")
	public Resp clear(String name){
		LOG.info("start clear cache name {}",name);
		if(StringUtils.hasLength(name)){
			Cache cache=this.cacheManager.getCache(name);
			if(cache!=null){
				cache.clear();
			}
		}else{//没有传 默认清空全部
			Collection<String> cacheNames=this.cacheManager.getCacheNames();
			if(cacheNames!=null){
				for(String cacheName:cacheNames){
					Cache cache=this.cacheManager.getCache(cacheName);
					if(cacheName!=null){
						cache.clear();
					}
				}
			}
		}
		LOG.info("end clear cache name {}",name);
		return Resp.OK();
	}
	/**
	 * 数据转换
	 * @param cache
	 * @return
	 */
	public CacheInfoVo convert(Cache cache){
		CacheInfoVo info=new CacheInfoVo();
		info.setName(cache.getName());
		info.setImpl(cacheManager.getClass().getName());
		return info;
	}
}
