package org.ada.study.netty.custom.init;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import org.ada.study.netty.custom.codec.LuckDecoder;
import org.ada.study.netty.custom.codec.LuckEncoder;
import org.ada.study.netty.custom.handler.NettyLuckHandler;

/**  
 * Filename: NettyLuckInitializer.java  <br>
 *
 * Description: 把编解码器和逻辑控制器放入初始化组件中  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月25日 <br>
 *
 *  
 */

public class NettyLuckInitializer extends ChannelInitializer<SocketChannel> {

    private static final LuckEncoder ENCODER = new LuckEncoder();

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
        pipeline.addLast(ENCODER);
        pipeline.addLast(new LuckDecoder());

        // 添加逻辑控制层
        pipeline.addLast(new NettyLuckHandler());

    }
}
