package org.ada.study.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**  
 * Filename: TimeServerHandler.java  <br>
 *
 * Description:   <br>
 * 本节中实现的协议是时间协议。它与前面的示例不同，因为它发送消息，其中包含一个32位整数，
 * 不接收任何请求，并且在发送消息后关闭连接。在这个示例中，您将学习如何构造和发送消息，
 * 并在完成时关闭连接。因为我们要忽略所有收到的数据，但发送消息一旦建立了连接，我们不能这
 * 个时候使用channelread()方法。相反，我们应该重写channelactive()方法。以下是实施：
 * 
 * (1)正如前面解释的，这channelactive()方法将被调用在连接建立和准备产生流量。让我们编写一个表示该方法当前时间的32位整数。
 * (2)要发送新消息，我们需要分配一个新的缓冲区，该缓冲区将包含消息。我们要写一个32位的整数，因此我们需要一个bytebuf其容量至少为4字节。
 * 通过channelhandlercontext获取当前bytebufallocator alloc()和分配新的缓冲区。
 * 
 * (3)和往常一样，我们编写构建的消息。
 * 	但是等等，空翻在哪里？我们不是用来调用java NIO字节缓冲区。。。之前在NiO发送消息flip()？bytebuf没有这样的方法，因为它有两个指针；一个读操作和写操作的其他。作者指数上升时，你写的东西一bytebuf而读者索引不改变。读取器索引和写入器索引分别表示消息的起始和结束位置。
 * 与此相反，NiO缓冲区没有提供一种干净的方法来计算消息内容的开始和结束，而不调用翻转方法。由于没有发送任何或不正确的数据，当您忘记翻转缓冲区时，您将遇到麻烦。这样的错误不会发生在网状的因为我们有不同的操作类型不同的指针。你会发现它使你的生活更容易，因为你习惯了它-一个没有翻转的生活！
 * 还有一点要注意的是，channelhandlercontext。write()（和writeandflush()）方法返回一个channelfuture。一个channelfuture代表一个I/O操作，尚未发生。这意味着，任何请求的操作不可能尚未进行的所有操作都是异步的，网状。例如，在发送消息之前，下面的代码可能会关闭连接：
 * Channel ch = ...;
	ch.writeAndFlush(message);
	ch.close();
 * (4)那么，当一个写请求完成时，我们如何得到通知呢？这是加入channelfuturelistener回channelfuture一样简单。在这里，我们创建了一个新的匿名channelfuturelistener关闭通道当操作完成。
	或者，您可以使用预定义的侦听器简化代码：
	f.addlistener（channelfuturelistener。关闭）；
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月21日 <br>
 *	
 *  
 */

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
