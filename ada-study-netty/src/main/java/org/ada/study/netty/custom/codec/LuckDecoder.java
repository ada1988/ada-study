package org.ada.study.netty.custom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.ada.study.netty.custom.entity.LuckHeader;
import org.ada.study.netty.custom.entity.LuckMessage;

/**  
 * Filename: LuckDecoder.java  <br>
 *
 * Description:   Decoder : 解码器，将入站的数据从一种格式转换成另外一种格式。<br>
 * 
作者：whthomas
链接：http://www.jianshu.com/p/ba21eb32ae97
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月25日 <br>
 *
 *  
 */

public class LuckDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 获取协议的版本
        int version = in.readInt();
        // 获取消息长度
        int contentLength = in.readInt();
        // 获取SessionId
        byte[] sessionByte = new byte[36];
        in.readBytes(sessionByte);
        String sessionId = new String(sessionByte);

        // 组装协议头
        LuckHeader header = new LuckHeader(version, contentLength, sessionId);

        // 读取消息内容
        byte[] content = in.readBytes(in.readableBytes()).array();

        LuckMessage message = new LuckMessage(header, new String(content));

        out.add(message);
    }
}
