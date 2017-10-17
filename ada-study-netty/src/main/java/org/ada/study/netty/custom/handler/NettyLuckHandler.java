package org.ada.study.netty.custom.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.ada.study.netty.custom.entity.LuckMessage;

/**  
 * Filename: NettyLuckHandler.java  <br>
 *
 * Description:  逻辑控制层，展现server接收到的协议信息 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月25日 <br>
 *
 *  
 */

public class NettyLuckHandler extends SimpleChannelInboundHandler<LuckMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LuckMessage msg) throws Exception {
        // 简单地打印出server接收到的消息
        System.out.println(msg.toString());
    }
}
