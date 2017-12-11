package com.miduo.channel.account.controller.www;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miduo.channel.account.business.service.IChannelAccountService;
import com.miduo.channel.account.common.constant.ProjectContants;
import com.miduo.channel.account.controller.www.vo.AccountVo;
import com.miduo.channel.account.controller.www.vo.LoginReq;
import com.miduo.common.model.Code;
import com.miduo.common.model.LoginInfo;
import com.miduo.common.model.Resp;

/**  
 * Filename: LoginController.java  <br>
 *
 * Description:  登录控制器 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月5日 <br>
 *  
 */
@RestController
@RequestMapping(ProjectContants.PROJECT_RESTFUL_BASE)
public class LoginController {
    
    @Autowired
    private IChannelAccountService channelAccountService;
	
	/**
     * 进入登录界面
     * @return
     */
    @RequestMapping("login")
    public Resp login(){
        return Resp.R(Code.LOGIN);
    }
    /**
     * 无权限
     * @return
     */
    @RequestMapping("unauthorized")
    public Resp unauthorized(){
        return Resp.R(Code.UNAUTHORIZED);
    }

    
    /**
     * 用户登录
     * @param loginReq
     * @return
     */
    @RequestMapping("login.do")
    public Resp login(@Valid LoginReq loginReq, BindingResult result, HttpSession session){
    	 if(result.hasErrors()){
             return Resp.ERR(result);
         }
    	 Subject subject= SecurityUtils.getSubject();
    	 /*
    	  * 场景：session已存在，切换用户重新登录。
    	  * 	退出登录，重新登录
    	  */
    	 SecurityUtils.getSubject().logout();
    	 
    	 if(!subject.isAuthenticated()){
             try {
                 subject.login(new UsernamePasswordToken(loginReq.getLoginName(), loginReq.getLoginPwd(), false));
             } catch (AuthenticationException e) {
                 return Resp.ERR(e.getMessage());
             }
         }
    	 
    	 //返回用户信息
    	 AccountVo accountVo = null;
         try {
        	 accountVo = (AccountVo)session.getAttribute( LoginInfo.SESSION_LOGIN_INFO_KEY );
         } catch (Exception e1) {
             e1.printStackTrace();
         }
         return Resp.OK(accountVo);
    }
    /**
     * 退出系统
     * @return
     */
    @RequestMapping("logout.do")
    public Resp logout(){
        SecurityUtils.getSubject().logout();
        return Resp.OK();
    }
}
