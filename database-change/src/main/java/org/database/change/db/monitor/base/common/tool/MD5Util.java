package org.database.change.db.monitor.base.common.tool;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * Filename: MD5Util.java <br>
 *
 * Description: <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年4月26日 <br>
 *
 * 
 */

public class MD5Util {
	public static String EncoderByMd5(String str){
		String newstr = null;
		try {
			// 确定计算方法
			MessageDigest md5 = MessageDigest.getInstance( "MD5" );
			// 加密后的字符串
			BASE64Encoder base64en = new BASE64Encoder();
			newstr = base64en.encode( md5.digest( str.getBytes( "utf-8" ) ) );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newstr;
	}
}
