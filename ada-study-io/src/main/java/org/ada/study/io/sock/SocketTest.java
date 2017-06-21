package org.ada.study.io.sock;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**  
 * Filename: Socket.java  <br>
 *
 * Description: 套接字链接方式  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月21日 <br>
 *
 *  
 */

public class SocketTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket( "", 8181 );//该连接方式，一直被阻塞
		
		Socket s1 = new Socket();//创建空套接字，避免一直阻塞,解决超时问题。但未解决，读写时，不中断问题。
		int timeout = 1000;
		s1.connect( new InetSocketAddress( "", 8181 ) , timeout);
		
		//管道本身提供读入写出的功能
		SocketChannel sc = SocketChannel.open( new InetSocketAddress( "", 8181 ) );//解决可中断问题
		
		//读取数据时，可以通过扫描器   （管道读取流）
		Scanner sin = new Scanner( sc );
		//写入数据时，可以通过管道转化（管道写入流）
		OutputStream out = Channels.newOutputStream( sc );//字节流
		
		
		//HTTP 写入数据后，关闭写入流，读出流仍然可以等待回复
		PrintWriter writer = new PrintWriter(out);//通过字符流，写入数据
		writer.print( "打印" );
		writer.flush();//刷新到流中发送出去。
		sc.shutdownOutput();//仅关闭写出流
		while(sin.hasNextLine()) System.out.println(sin.nextLine());//等待读数据
		sc.close();
		
		
		
	}
}
