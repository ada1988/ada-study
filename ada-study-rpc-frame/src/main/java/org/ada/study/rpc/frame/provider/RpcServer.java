package org.ada.study.rpc.frame.provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashMap;
import java.util.Map;

import org.ada.study.rpc.frame.common.annotation.RpcService;
import org.ada.study.rpc.frame.common.coder.RpcDecoder;
import org.ada.study.rpc.frame.common.coder.RpcEncoder;
import org.ada.study.rpc.frame.common.msg.RpcRequest;
import org.ada.study.rpc.frame.common.msg.RpcResponse;
import org.ada.study.rpc.frame.registry.ServiceRegistry;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**  
 * Filename: RpcServer.java  <br>
 *
 * Description: 服务提供者 的服务  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月17日 <br>
 *
 *  
 */

public class RpcServer implements ApplicationContextAware, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);

    private String serverAddress;
    private ServiceRegistry serviceRegistry;
	private Map<String, Object> handlerMap = new HashMap<String, Object>(); // 存放接口名与服务对象之间的映射关系
	
	public RpcServer(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcServer(String serverAddress, ServiceRegistry serviceRegistry) {
        this.serverAddress = serverAddress;
        this.serviceRegistry = serviceRegistry;
    }
	
	@Override
	public void afterPropertiesSet() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();//启动服务端
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline()
                            .addLast(new RpcDecoder(RpcRequest.class)) // 将 RPC 请求进行解码（为了处理请求）
                            .addLast(new RpcEncoder(RpcResponse.class)) // 将 RPC 响应进行编码（为了返回响应）
                            .addLast(new RpcProviderHandler(handlerMap)); // 处理 RPC 请求
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            String[] array = serverAddress.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);

            ChannelFuture future = bootstrap.bind(host, port).sync();
            LOGGER.debug("server started on port {}", port);

            if (serviceRegistry != null) {
                serviceRegistry.register(serverAddress); // 注册服务地址
            }

            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}

	/**
	 * 扫描所有存在RpcService注解的类
	 */
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class); // 获取所有带有 RpcService 注解的 Spring Bean
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
	}

}
