package com.miduo.channel.account.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.miduo.channel.account.business.service.IChannelAccountService;
import com.miduo.channel.account.controller.www.vo.AccountVo;
import com.miduo.common.model.LoginInfo;
import com.miduo.common.shiro.UsernamePasswordAuthorizingRealm;

/**
 * Filename: UserRealm.java <br>
 *
 * Description: 用户授权 <br>
 * 
 * 
 * 一、doGetAuthorizationInfo执行时机有三个 
 * 1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
 * 2、@RequiresRoles("admin") ：在方法上加注解的时候； 
 * 3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
 * 
 * 二、doGetAuthenticationInfo执行时机如下 当调用Subject currentUser = SecurityUtils.getSubject(); currentUser.login(token);
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年12月5日 <br>
 *
 * 
 */
public class UserRealm extends UsernamePasswordAuthorizingRealm {

	@Autowired
	@Lazy//使用时加载该对象，由于shiro、spring生命周期管理上存在问题（shiro先加载，spring获取不到）
	private IChannelAccountService channelAccountService;

	@Bean("authorizingRealm")
	public AuthorizingRealm authorizingRealm() {
		return new UserRealm();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(UsernamePasswordToken token) throws AuthenticationException {
		//错误次数
		Long loginNum = channelAccountService.loginErrorNum( token.getUsername() );
		if(loginNum.intValue() >= 5){
			throw new UnknownAccountException("您的账户已被锁定，请联系400-086-1236！");
		}
		//验证用户
		AccountVo account = channelAccountService.mergeLogin( token.getUsername(), new String(token.getPassword()) );
		
		if(null == account || null == account.getStatus()){
			//错误累加
			channelAccountService.loginErrorIncrease( token.getUsername() );
			throw new UnknownAccountException("账号或密码不正确");
		}
		if(account.getStatus().intValue()==1){
			throw new UnknownAccountException("您的账户已被锁定，请联系400-086-1236！");
		}
		//存入会话里面
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(LoginInfo.SESSION_LOGIN_INFO_KEY, account);
		return new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
}
