package org.ada.study.netty.custom.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

import org.ada.study.netty.custom.entity.LuckHeader;
import org.ada.study.netty.custom.entity.LuckMessage;
import org.ada.study.netty.custom.init.NettyLuckInitializer;

/**  
 * Filename: LuckClient.java  <br>
 *
 * Description:  客户端 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月25日 <br>
 *
 *  
 */

public class LuckClient {
	public static void main(String args[]) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyLuckInitializer());

            // Start the connection attempt.
            Channel ch = b.connect("127.0.0.1", 8888).sync().channel();

            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String content = "I'm the luck protocol!";

            LuckHeader header = new LuckHeader(version, content.length(), sessionId);
            LuckMessage message = new LuckMessage(header, content);
            ch.writeAndFlush(message);
            Thread.sleep( 1000*60*30 );
            ch.close();

        } finally {
            group.shutdownGracefully();
        }
    }
}
