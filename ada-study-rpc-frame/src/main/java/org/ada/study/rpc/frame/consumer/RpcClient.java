package org.ada.study.rpc.frame.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.ada.study.rpc.frame.common.coder.RpcDecoder;
import org.ada.study.rpc.frame.common.coder.RpcEncoder;
import org.ada.study.rpc.frame.common.msg.RpcRequest;
import org.ada.study.rpc.frame.common.msg.RpcResponse;

/**
 * Filename: RpcClient.java <br>
 *
 * Description: RPC服务 调用者 角色 <br>
 * 
 * netty 简单读出对象绑定
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月17日 <br>
 *
 * 
 */

public class RpcClient {
	private String				host;
	private int					port;
	private ThreadLocal<RpcResponse> response = new ThreadLocal<RpcResponse>();//变量在多线程中共享
    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    /**
     * 发送 请求 （写入请求）
     * @param request
     * @return
     * @throws Exception
     * @author: CZD  
     * @Createtime: 2017年10月17日
     */
    public RpcResponse send(RpcRequest request) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();//启动客户端
            bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline()
                            .addLast(new RpcEncoder(RpcRequest.class)) // 将 RPC 请求进行编码（为了发送请求）
                            .addLast(new RpcDecoder(RpcResponse.class)) // 将 RPC 响应进行解码（为了处理响应）
                            .addLast(new RpcCustomerHandler(response)); // 使用 RpcClient 发送 RPC 请求
                    }
                })
                .option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().writeAndFlush(request).sync();

            synchronized (response) {
            	response.wait(); // 未收到响应，使线程等待
            }

            if (response.get() != null) {
                future.channel().closeFuture().sync();
            }
            return response.get();
        } finally {
            group.shutdownGracefully();
        }
    }
}
