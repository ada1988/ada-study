package org.ada.study.io.sock;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**  
 * Filename: InelAddressTest.java  <br>
 *
 * Description: 通过域名获取ip  <br>
 * 
 * URL URI 区别
 * 统一资源定位符 （定位资源，可以打开连接，仅支持http:\https:\ftp:\file:\jar:） 
 * 统一资源标识符（解析路径）
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月21日 <br>
 *
 *  
 */

public class InelAddressTest {
	/**
	 * 获取 域名 对于ip
	 * 
	 * @author: CZD  
	 * @throws UnknownHostException 
	 * @Createtime: 2017年6月21日
	 */
	public static void getDomainIp() throws UnknownHostException{
		InetAddress[] addresses = Inet6Address.getAllByName( "www.miduo.com" );
		for(InetAddress address:addresses){
			System.out.println(address);
		}
	}
	
	/**
	 * URI 解析 资源
	 * @throws URISyntaxException
	 * @author: CZD  
	 * @Createtime: 2017年6月21日
	 */
	public static void uriAndUrlTest() throws URISyntaxException{
		String str = "http://www.miduo.com";
		String str2 = "http://www.miduo.com/product/list.htm";
		URI uri = new URI( str );
		URI uri2 = new URI( str2 );
		System.out.println(uri.getHost()+"----"+uri.getPort()+"----"+uri.getFragment()+"---"+uri.getRawPath());
		System.out.println(uri2.getHost()+"----"+uri2.getPort()+"----"+uri2.getFragment()+"---"+uri2.getRawPath());
	}
	
	public static void main(String[] args) throws Exception {
		InelAddressTest.uriAndUrlTest();
	}
}
