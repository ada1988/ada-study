package org.ada.study.rpc.frame.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.ada.study.rpc.frame.common.msg.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filename: RpcProvider.java <br>
 *
 * Description: RPC服务 调用者 角色<br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月17日 <br>
 *
 * 
 */

public class RpcCustomerHandler extends SimpleChannelInboundHandler<RpcResponse> {
	
	private static final Logger	LOGGER	= LoggerFactory.getLogger( RpcCustomerHandler.class );

	private ThreadLocal<RpcResponse> response;
	
	public RpcCustomerHandler(ThreadLocal<RpcResponse> response){
		this.response = response;
	}

	 /**
     * 获取响应 （读出消息）
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
		this.response.set( response );

        synchronized (this.response) {
        	this.response.notifyAll(); // 收到响应，唤醒线程
        }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		LOGGER.error( "client caught exception", cause );
		ctx.close();
	}
}
