package org.ada.study.rpc.frame;


import org.ada.study.rpc.business.service.IHelloService;
import org.ada.study.rpc.frame.proxy.RpcProxy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-consumer.xml")
public class HelloServiceTest 
{
		@Autowired
	    private RpcProxy rpcProxy;

	    @Test
	    public void helloTest() {
	        IHelloService helloService = rpcProxy.create(IHelloService.class);
	        String result = helloService.hello("World");
	        Assert.assertEquals("Hello! World", result);
	    }
}
