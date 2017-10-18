package org.ada.study.rpc.frame.provider;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import org.ada.study.rpc.frame.common.msg.RpcRequest;
import org.ada.study.rpc.frame.common.msg.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filename: RpcProvider.java <br>
 *
 * Description: RPC服务 提供者 角色<br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月17日 <br>
 *
 * 
 */

public class RpcProviderHandler extends SimpleChannelInboundHandler<RpcRequest> {
	private static final Logger			LOGGER	= LoggerFactory.getLogger( RpcProviderHandler.class );

	private final Map<String, Object>	providerMap;

	public RpcProviderHandler(Map<String, Object> providerMap) {
		this.providerMap = providerMap;
	}

	@Override
	public void channelRead0(final ChannelHandlerContext ctx, RpcRequest request) throws Exception {
		RpcResponse response = new RpcResponse();
		response.setRequestId( request.getRequestId() );
		try {
			Object result = handle( request );
			response.setResult( result );
		} catch (Throwable t) {
			response.setError( t );
		}
		ctx.writeAndFlush( response ).addListener( ChannelFutureListener.CLOSE );
	}

	private Object handle(RpcRequest request) throws Throwable {
		String className = request.getClassName();
		Object serviceBean = providerMap.get( className );

		Class<?> serviceClass = serviceBean.getClass();
		String methodName = request.getMethodName();
		Class<?>[] parameterTypes = request.getParameterTypes();
		Object[] parameters = request.getParameters();

		/*
		 * Method method = serviceClass.getMethod(methodName, parameterTypes);
		 * method.setAccessible(true); return method.invoke(serviceBean,
		 * parameters);
		 */

		FastClass serviceFastClass = FastClass.create( serviceClass );
		FastMethod serviceFastMethod = serviceFastClass.getMethod( methodName, parameterTypes );
		return serviceFastMethod.invoke( serviceBean, parameters );
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		LOGGER.error( "server caught exception", cause );
		ctx.close();
	}
}
