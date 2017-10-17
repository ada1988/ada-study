package org.ada.study.netty.custom.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.ada.study.netty.custom.entity.LuckMessage;

/**  
 * Filename: LuckClientHandler.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月25日 <br>
 *
 *  
 */

public class NettyLuckClientHandler extends SimpleChannelInboundHandler<LuckMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LuckMessage message) throws Exception {
        System.out.println(message);
    }
}
