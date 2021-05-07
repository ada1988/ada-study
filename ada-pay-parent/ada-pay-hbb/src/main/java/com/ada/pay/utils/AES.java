package com.ada.pay.utils;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *AES加密解密工具类
 *密钥长度固定为16位
 *@author M-Y
 */
public class AES {
	static private final String AES_IV = "0000000000000000";
	public static Logger logger = LoggerFactory.getLogger(AES.class);

	public static String encrypt(String content, String aesKey)
	{
		try{
			SecretKeySpec secKey = new SecretKeySpec(aesKey.getBytes(), "AES");
			IvParameterSpec iv = new IvParameterSpec(AES_IV.getBytes());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secKey, iv);
			return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
		}catch(Exception e){
			logger.error("AES encrypt异常",e);
			throw new RuntimeException(e);
		}
	}
	
	


	public static String decrypt(String content, String aesKey)
	{
		try{
			byte[] b = Base64.getDecoder().decode(content.getBytes());
			SecretKeySpec secKey = new SecretKeySpec(aesKey.getBytes(), "AES");
			IvParameterSpec iv = new IvParameterSpec(AES_IV.getBytes());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secKey, iv);
			return new String(cipher.doFinal(b));
		}catch(Exception e){
			logger.error("AES decrypt异常",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成AES key
	 * @return String
	 */
	 /**
	  * 随机生成秘钥
	  */
	  public static String getKey() {
		String s = null;
	    try {
	      KeyGenerator kg = KeyGenerator.getInstance("AES");
	      kg.init(128);
	      //要生成多少位，只需要修改这里即可128, 192或256
	      SecretKey sk = kg.generateKey();
	      byte[] b = sk.getEncoded();
	      s = byteToHexString(b);
	    }
	    catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	      System.out.println("没有此算法。");
	    }
	    return s;
	  }
	  /**
	  * 使用指定的字符串生成秘钥
	  */
	  public static String getKeyByPass(String password) {
		String result = null;
	    try {
	      KeyGenerator kg = KeyGenerator.getInstance("AES");
	      // kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
	      //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
	      kg.init(128, new SecureRandom(password.getBytes()));
	      SecretKey sk = kg.generateKey();
	      byte[] b = sk.getEncoded();
	      result = byteToHexString(b);
	    }
	    catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	      System.out.println("没有此算法。");
	    }
	    return result;
	  }
	  
	  /**
	   * byte数组转化为16进制字符串
	   * @param bytes
	   * @return
	   */
	   public static String byteToHexString(byte[] bytes) {
	     StringBuffer sb = new StringBuffer();
	     for (int i = 0; i < bytes.length; i++) {
	       String strHex=Integer.toHexString(bytes[i]);
	       if(strHex.length() > 3) {
	         sb.append(strHex.substring(6));
	       } else {
	         if(strHex.length() < 2) {
	           sb.append("0" + strHex);
	         } else {
	           sb.append(strHex);
	         }
	       }
	     }
	     return sb.toString();
	   }
	   
	   public static void main(String[] args) {
		System.out.println(getKey() );
	}
}
