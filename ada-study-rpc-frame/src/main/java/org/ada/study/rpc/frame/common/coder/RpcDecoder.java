package org.ada.study.rpc.frame.common.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.ada.study.rpc.frame.common.SerializationUtil;

/**  
 * Filename: RpcDecoder.java  <br>
 *
 * Description:  解码器 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月17日 <br>
 *
 *  
 */

public class RpcDecoder extends ByteToMessageDecoder {
	 private Class<?> genericClass;

	    public RpcDecoder(Class<?> genericClass) {
	        this.genericClass = genericClass;
	    }
	    @Override
	    public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
	    	//获取可读取的缓存数据 长度（(this.writerIndex - this.readerIndex) 写入索引位置-读取索引位置）
	        if (in.readableBytes() < 4) {
	            return;//待会再读
	        }
	        in.markReaderIndex();//标记一下，我要开始读取数据，为了不可读取时回执读取索引的位置
	        int dataLength = in.readInt();//获取字节码的长度
	        if (dataLength < 0) {
	            ctx.close();
	        }
	        //检查可读取的数据是否准备好？？？？？
	        if (in.readableBytes() < dataLength) {
	            in.resetReaderIndex();//重置读取的索引位置
	        }
	        byte[] data = new byte[dataLength];
	        in.readBytes(data);//将数据读取到data中

	        Object obj = SerializationUtil.deserialize(data, genericClass);
	        out.add(obj);
	    }
}
