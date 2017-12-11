package com.miduo.channel.account.controller.www.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LoginReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5715006518843544963L;

	/**
	 * 登录用户名
	 */
	@NotNull
	@Length(min=1,max=64)
	private String loginName;
	
	/**
	 * 登录密码
	 */
	@NotNull
	private String loginPwd;
	/**
	 * 验证码
	 */
	@Length(min=6,max=12)
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	
	
}
