package org.ada.study.netty.custom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.ada.study.netty.custom.entity.LuckHeader;
import org.ada.study.netty.custom.entity.LuckMessage;

/**  
 * Filename: LuckEncoder.java  <br>
 *
 * Description: Encoder : 编码器，将出站的数据从一种格式转换成另外一种格式。  <br>
 * 
 * 作者：whthomas
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

public class LuckEncoder extends MessageToByteEncoder<LuckMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, LuckMessage message, ByteBuf out) throws Exception {

        // 将Message转换成二进制数据
        LuckHeader header = message.getLuckHeader();

        // 这里写入的顺序就是协议的顺序.

        // 写入Header信息
        out.writeInt(header.getVersion());
        out.writeInt(message.getContent().length());
        out.writeBytes(header.getSessionId().getBytes());

        // 写入消息主体信息
        out.writeBytes(message.getContent().getBytes());
    }
}
