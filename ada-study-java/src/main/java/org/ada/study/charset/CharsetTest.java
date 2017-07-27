package org.ada.study.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * FileName CharsetTest
 * 
 * @author: CZD
 * @Createtime: 2017年6月17日
 */
public class CharsetTest {
	public static void main(String[] args){
		//CharsetTest.printCharSet();
		CharsetTest.charEncode();
		CharsetTest.charDecode();
	}
	
	/**
	 * 将给定的字符，编码为utf-8字节序列
	 */
	public static void charEncode(){
		String str = "崔";
		Charset cset = Charset.forName("utf-8");
		ByteBuffer buffer = cset.encode(str);
		byte[] bytes = buffer.array();
		for(byte bt:bytes){
			System.out.println(bt);
		}
	}
	/**
	 * 解码给定的字节序列
	 */
	public static void charDecode(){
		byte[] bt = new byte[]{-27,-76,-108};
		ByteBuffer bbuf = ByteBuffer.wrap(bt);
		Charset cset = Charset.forName("utf-8");
		CharBuffer cbuf = cset.decode(bbuf);
		System.out.println(cbuf.toString());
	}
	/**
	 * 打印所有字符集
	 */
	public static void printCharSet(){
		SortedMap<String,Charset> charsets = Charset.availableCharsets();
		for(String key:charsets.keySet()){
			System.out.println(key);
		}
	}
}
