package org.ada.study.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**  
 * Filename: TimeClient.java  <br>
 *
 * Description:  
 * Unlike DISCARD and ECHO servers, we need a client for the TIME protocol 
 * because a human cannot translate a 32-bit binary data into a date on a calendar.
 * In this section, we discuss how to make sure the server works correctly and learn
 *  how to write a client with Netty.

	The biggest and only difference between a server and a client in Netty is that 
	different Bootstrap and Channel implementations are used. Please take a look at
	 the following code: <br>
 * (1)Bootstrap类似serverbootstrap除了它的非服务器渠道如客户端或连接的通道。
 * (2)If you specify only one EventLoopGroup, it will be used both as a boss group and as a worker group. The boss worker is not used for the client side though.
 	如果你只指定一个eventloopgroup，它将作为一个老板和工人组组。老板工作人员不用于客户端。
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月21日 <br>
 *
 *  
 */

public class TimeClient {
	 public static void main(String[] args) throws Exception {
	        String host = args[0];
	        int port = Integer.parseInt(args[1]);
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        
	        try {
	            Bootstrap b = new Bootstrap(); // (1)
	            b.group(workerGroup); // (2)
	            b.channel(NioSocketChannel.class); // (3)Instead of NioServerSocketChannel, NioSocketChannel is being used to create a client-side Channel.
	            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)Note that we do not use childOption() here unlike we did with ServerBootstrap because the client-side SocketChannel does not have a parent.
	            b.handler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                public void initChannel(SocketChannel ch) throws Exception {
	                    ch.pipeline().addLast(new TimeClientHandler());
	                }
	            });
	            
	            // Start the client.
	            ChannelFuture f = b.connect(host, port).sync(); // (5)

	            // Wait until the connection is closed.
	            f.channel().closeFuture().sync();
	        } finally {
	            workerGroup.shutdownGracefully();
	        }
	    }
}
