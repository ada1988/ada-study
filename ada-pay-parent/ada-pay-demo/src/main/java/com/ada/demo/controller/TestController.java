package com.ada.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.demo.utils.IdGenerator;
import com.ada.pay.bean.TC020101ReqData;
import com.ada.pay.bean.TC020101RspData;
import com.ada.pay.common.APICommonTool;
import com.ada.pay.service.TC020101Service;

/**
 * 测试类
 */
@RestController
@RequestMapping("/test")
public class TestController {
    
	@Autowired
	private APICommonTool commonTool;
	
    @Autowired
    private TC020101Service tC020101Service;
    
    /**
     * 获取短信验证码(020101)
     * 示例：
     * {
			"mobilePhone":"15614125980",
			"type":"1"
		}
     */
    @PostMapping("/getSmsCode")
    public TC020101RspData getSmsCode(@RequestBody TC020101ReqData req) {
    	TC020101RspData res = null;
    	try {
    		req.setReqNo(IdGenerator.getUUID());
    		req.setTradeCode("020101");
			res = tC020101Service.requestApi(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return res;
    }
    
}