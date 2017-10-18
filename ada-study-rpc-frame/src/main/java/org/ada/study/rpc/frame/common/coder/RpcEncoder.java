package org.ada.study.rpc.frame.common.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.ada.study.rpc.frame.common.SerializationUtil;

/**
 * Filename: RpcEncoder.java <br>
 *
 * Description: 编码器 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月17日 <br>
 *
 * 
 */

public class RpcEncoder extends MessageToByteEncoder {
	private Class<?>	genericClass;

	public RpcEncoder(Class<?> genericClass) {
		this.genericClass = genericClass;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
		//断言：是否是该Class的字节码数据
		if (genericClass.isInstance(in)) {
				byte[] data = SerializationUtil.serialize(in);
	            out.writeInt(data.length);//发送类的字节长度
	            out.writeBytes(data);//发送类的字节数组
		}
	}

}
