package org.ada.study.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**  
 * Filename: DiscardServer.java  <br>
 *
 * Description:   <br>
 * 到现在为止，一直都还不错.我们已经实现了丢弃服务器的前半部分。剩下的就是写的main()方法来启动服务器。
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月21日 <br>
 *
 * (1)nioeventloopgroup是一个多线程的事件循环处理I/O操作。Netty提供不同种类的传输各种eventloopgroup实现。
 * 我们在这个例子中，服务器端应用程序的实现，因此将使用两nioeventloopgroup。第一个，通常称为“老板”，接受传入连接。
 * 第二个，通常称为“工人”，一旦老板接受连接并将已接受的连接注册给工人，就处理已接受连接的流量。多线程的使用和它们是如何
 * 映射到创建渠道，取决于eventloopgroup实现，甚至可通过构造函数。
 *
 *  (2)serverbootstrap是一个辅助类，设置了一个服务器。您可以直接使用通道设置服务器。但是，请注意这是一个繁琐的过程，在大多数情况下不需要这样做。
 *  
 *  (3)在这里，我们指定要使用的nioserversocketchannel类进行实例化一个新的渠道来接受传入的连接。
 *  
 *  (4)此处指定的处理程序总是由一个新接受的通道进行评估。是一种特殊的channelinitializer处理器，旨在帮助用户配置一个新的通道。
 *  这是最有可能的，你想添加一些程序如discardserverhandler实施你的网络应用程序配置的新通道ChannelPipeline。当应用程序变
 *  得复杂时，您可能会将更多的处理程序添加到管道中，并最终将这个匿名类提取到顶级类中。
 *  
 *  (5)您还可以设置特定于通道实现的参数。我们写一个TCP / IP的服务器，所以我们允许设置套接字选项如tcpnodelay和KeepAlive。
 *  请参阅channeloption的apidocs和具体channelconfig实现得到支持的channeloptions概述。
 *  
 *  (6)你有没有注意到option()和childoption()？option()是接受传入连接的nioserversocketchannel。childoption()是频
 *  道的母serverchannel接受，这是在这种情况下，nioserversocketchannel。
 *  
 *  (7)我们现在准备出发了。剩下的就是绑定到端口并启动服务器。在这里，我们所有的网卡绑定到端口8080（网络接口卡）在机。你现在可以叫（）方法很多你想要的东西（不同的绑定地址。
 */

public class DiscardServer {
	 private int port;
	    
	    public DiscardServer(int port) {
	        this.port = port;
	    }
	    
	    public void run() throws Exception {
	        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap(); // (2)
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class) // (3)
	             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
	                 @Override
	                 public void initChannel(SocketChannel ch) throws Exception {
	                     ch.pipeline().addLast(new DiscardServerHandler());
	                 }
	             })
	             .option(ChannelOption.SO_BACKLOG, 128)          // (5)
	             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
	    
	            // Bind and start to accept incoming connections.
	            ChannelFuture f = b.bind(port).sync(); // (7)
	    
	            // Wait until the server socket is closed.
	            // In this example, this does not happen, but you can do that to gracefully
	            // shut down your server.
	            f.channel().closeFuture().sync();
	        } finally {
	            workerGroup.shutdownGracefully();
	            bossGroup.shutdownGracefully();
	        }
	    }
	    
	    public static void main(String[] args) throws Exception {
	        int port;
	        if (args.length > 0) {
	            port = Integer.parseInt(args[0]);
	        } else {
	            port = 8080;
	        }
	        new DiscardServer(port).run();
	        
	        //启动CMD命令 telnet 127.0.0.1 8080  
	        //随便录入信息，便可接收到
	    }
}
