package org.ada.study.io.nio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**  
 * Filename: FileReadSpeedTest.java  <br>
 *
 * Description: 测试文件读取速度  <br>
 * 
 * Java 核心技术  源码
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月20日 <br>
 *
 *  
 */

public class FileReadSpeedTest {
	
	/**
	 * rt.jar  大小：57.3M 
	 * @param args
	 * @throws IOException
	 * @author: CZD  
	 * @Createtime: 2017年6月20日
	 */
	public static void main(String[] args)  throws IOException{
		//String path = "D:\\java_source\\jdk_home\\java7\\jdk1.7.0_79\\jre\\lib\\rt.jar";
		String path = "D:\\java_source\\jdk_home\\java7\\jdk1.7.0_79\\jre\\lib\\plugin.jar";
		System.out.println( "Input Stream:" );
		long start = System.currentTimeMillis();
		Path fileName = Paths.get( path );
		long crcValue = checksumInputString( fileName );
		long end = System.currentTimeMillis();
		System.out.println(Long.toHexString( crcValue ));
		System.out.println((end-start) + " milliseconds");
		
		System.out.println( "BufferedInputStream:" );
		crcValue = checksumBufferedInputStream( fileName );
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString( crcValue ));
		System.out.println((end-start) + " milliseconds");
		
		System.out.println( "RandomInputStream:" );
		crcValue = checksumRandomInputStream( fileName );
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString( crcValue ));
		System.out.println((end-start) + " milliseconds");
		
		System.out.println( "MappedFile:" );
		crcValue = checksumMappedFile( fileName );
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString( crcValue ));
		System.out.println((end-start) + " milliseconds");
	}
	
	/**
	 * 正常流 读取
	 * @param fileName
	 * @author: CZD  
	 * @Createtime: 2017年6月20日
	 */
	public static long checksumInputString(Path fileName) throws IOException{
		try(InputStream in = Files.newInputStream( fileName )){
			CRC32 crc = new CRC32();
			
			int c;
			while((c=in.read())!=-1)
				crc.update( c );
			return crc.getValue();
		}
	}
	
	/**
	 * 基于缓存流 读取
	 * @param fileName
	 * @author: CZD  
	 * @Createtime: 2017年6月20日
	 */
	public static long checksumBufferedInputStream(Path fileName) throws IOException{
		try(InputStream in = new BufferedInputStream( Files.newInputStream( fileName ) )){
			CRC32 crc = new CRC32();
			
			int c;
			while((c = in.read())!=-1)
				crc.update( c );
			return crc.getValue();
		}
	}
	
	/**
	 * 基于随机访问文件 读取
	 * @param fileName
	 * @author: CZD  
	 * @Createtime: 2017年6月20日
	 */
	public static long checksumRandomInputStream(Path fileName) throws IOException{
		try(RandomAccessFile file = new RandomAccessFile(fileName.toFile(),"r" )){
			long length = file.length();
			CRC32 crc = new CRC32();
			
			for(long p = 0;p<length; p++){
				file.seek( p );
				int c = file.readByte();
				crc.update( c );
			}
			return crc.getValue();
		}
	}
	
	/**
	 * 基于内存映射文件 读取
	 * @param fileName
	 * @author: CZD  
	 * @Createtime: 2017年6月20日
	 */
	public static long checksumMappedFile(Path fileName) throws IOException{
		try(FileChannel channel = FileChannel.open( fileName )){
			CRC32 crc = new CRC32();
			int length = (int)channel.size();
			MappedByteBuffer buffer = channel.map( FileChannel.MapMode.READ_ONLY, 0, length );
			
			for(int p = 0; p<length ;p++){
				int c = buffer.get(p);
				crc.update( c );
			}
			
			return crc.getValue();
		}
	}
}
