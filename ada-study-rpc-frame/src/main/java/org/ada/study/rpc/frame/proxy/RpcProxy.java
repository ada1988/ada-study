package org.ada.study.rpc.frame.proxy;

import java.lang.reflect.Method;
import java.util.UUID;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import org.ada.study.rpc.frame.common.msg.RpcRequest;
import org.ada.study.rpc.frame.common.msg.RpcResponse;
import org.ada.study.rpc.frame.consumer.RpcClient;
import org.ada.study.rpc.frame.registry.ServiceDiscovery;

/**
 * Filename: RpcProxy.java <br>
 *
 * Description: RPC 代理 <br>
 * CGLIB 实现
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月17日 <br>
 *
 * 
 */

public class RpcProxy {
	private String				serverAddress;
	private ServiceDiscovery	serviceDiscovery;

	public RpcProxy(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }
	
	/**
	 * 创建代理类
	 * @param interfaceClass
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年10月17日
	 */
	public <T> T create(Class<?> interfaceClass) {
		@SuppressWarnings("unchecked")
		T proxy = (T)Proxy.newProxyInstance( interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                request.setRequestId(UUID.randomUUID().toString());
                request.setClassName(method.getDeclaringClass().getName());
                request.setMethodName(method.getName());
                request.setParameterTypes(method.getParameterTypes());
                request.setParameters(args);
                
                if (serviceDiscovery != null) {
                    serverAddress = serviceDiscovery.discover(); // 发现服务
                }
                
                String[] array = serverAddress.split(":");
                String host = array[0];
                int port = Integer.parseInt(array[1]);
                
                RpcClient client = new RpcClient(host, port); // 初始化 RPC 客户端
                RpcResponse response = client.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

                if (response.isError()) {
                    throw response.getError();
                } else {
                    return response.getResult();
                }
			}} );
		return proxy;
	}
}
