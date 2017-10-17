package org.ada.study.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Filename: TimeClientHandler.java <br>
 *
 * Description: 正如您所看到的，它与服务器端代码实际上没有什么不同。的channelhandler实施呢？它应该从服务器接收32位整数，
 * 将其转换成人类可读的格式，打印转换后的时间，并关闭连接： <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年9月22日 <br>
 *
 * 
 */

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf m = (ByteBuf) msg; // (1)
		try {
			long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println( new Date( currentTimeMillis ) );
			ctx.close();
		} finally {
			m.release();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
