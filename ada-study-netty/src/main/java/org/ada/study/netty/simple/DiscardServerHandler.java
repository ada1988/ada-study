package org.ada.study.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 丢弃协议
 * 
 * (1)、      discardserverhandler是channelinboundhandleradapter的延伸，
 * 		这是一个实现channelinboundhandler。channelinboundhandler提供不同的事件处理程序方法，可以重写。
 * 		现在，这只够延长channelinboundhandleradapter而不是自己来实现处理器接口。
 * 
 * (2)、     我们覆盖channelread()事件处理程序方法。每当从客户机接收到新数据时，
 * 			都会使用接收到的消息来调用此方法。在这个例子中，接收到的消息的类型是ByteBuf。
 *
 *	(3)、    为了实现丢弃协议，处理程序必须忽略接收到的消息。bytebuf是引用计数的对象已被释放，
 *			通过显式release()方法。请记住，处理程序的职责是释放传递给处理程序的引用计数对象。
 *			通常，channelread()处理实现方法如下：
 *			@Override
			public void channelRead(ChannelHandlerContext ctx, Object msg) {
			    try {
			        // Do something with msg
			    } finally {
			        ReferenceCountUtil.release(msg);
			    }
			}
			
	(4)、    exceptioncaught()事件处理程序方法调用抛出异常时，由网状由于I/O错误或处理程序执行被处理事件时引发的异常。
			在大多数情况下，应该记录捕获的异常，并且它的相关通道应该在这里关闭，尽管这个方法的实现可以根据您要处理异常情
			况的不同而有所不同。例如，您可能希望在关闭连接之前用错误代码发送响应消息。
			
			
 */
public class DiscardServerHandler  extends ChannelInboundHandlerAdapter { // (1)
	/**
     * 	(1) 一个channelhandlercontext对象提供了各种操作可以触发各种I/O事件和操作。
     * 		在这里，我们调用写（对象）来逐字地接收所接收的消息。请注意，我们没有释放接收到的消息，
     * 		不像我们在丢弃示例中所做的那样。这是因为网络为你释放它的时候写出来的线。
     * 	(2)写入（对象）不能将消息写入到电线中。这是内部缓冲，然后冲到电线flush() CTX。或者，你可以打电话给CTX。writeandflush（MSG）简洁。
     *     public void channelRead(ChannelHandlerContext ctx, Object msg) {
		        ctx.write(msg); // (1)
		        ctx.flush(); // (2)
		    }
     * 
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)这种低效率的循环实际上可以简化为：系统。出来。println（在ToString（IO。网状。利用。charsetutil。us_ascii））
                System.out.print((char) in.readByte());
                System.out.flush();
                
                
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)或者，你可以做release()这里。
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        cause.printStackTrace();
        ctx.close();
    }
}
