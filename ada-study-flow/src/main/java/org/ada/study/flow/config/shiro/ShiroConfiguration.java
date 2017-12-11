package com.miduo.channel.account.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.miduo.common.shiro.ShiroRedisConfiguration;
import com.miduo.common.shiro.ShiroRedisLocalSessionCacheFilter;

/**  
 * Filename: ShiroConfiguration.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月5日 <br>
 * @copy XKS
 *  
 */
@Configuration
public class ShiroConfiguration extends ShiroRedisConfiguration {
	@Bean("authorizingRealm")
	public AuthorizingRealm authorizingRealm(){
		return new UserRealm();
	}
	
	/**
	 * 本地清除Filter,如果使用redisSession一定配置该方法   (XKS写的，不知道为什么)
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年12月5日
	 * @copy XKS
	 */
	@Bean("shiroRedisLocalSessionCacheFilter")
    public ShiroRedisLocalSessionCacheFilter shiroRedisLocalSessionCacheFilter(){
        return new ShiroRedisLocalSessionCacheFilter();
    }
	
	/**
	 * 权限过滤
	 * @param securityManager
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年12月5日
	 * @copy XKS
	 */
	@Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/channel/account/login");//登录地址
        // shiroFilter.setSuccessUrl("/index.html");
        shiroFilter.setUnauthorizedUrl("/channel/account/unauthorized");
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/channel/account/login.do", "anon");
        filterMap.put("/channel/account/validateVersion.do", "anon");
        filterMap.put("/channel/account/findPassword.do", "anon");
        filterMap.put("/channel/account/init.do", "anon");
        filterMap.put("/rpc/channel/account/**", "anon");//这个不做权限校验，路由不对外网访问
        filterMap.put("/channel/account/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }
}
