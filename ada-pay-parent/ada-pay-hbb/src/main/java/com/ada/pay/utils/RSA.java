package com.ada.pay.utils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;


public class RSA {
    //生成秘钥对
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    //获取公钥(Base64编码)
    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byteToBase64(bytes);
    }

    //获取私钥(Base64编码)
    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byteToBase64(bytes);
    }

    //将Base64编码后的公钥转换成PublicKey对象
    public static PublicKey stringToPublicKey(String pubStr) throws Exception {
    	  byte[] keyBytes = base64ToByte(pubStr);
          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          return keyFactory.generatePublic(keySpec);
    }

    //将Base64编码后的私钥转换成PrivateKey对象
    public static PrivateKey stringToPrivateKey(String priStr) throws Exception {
        byte[] keyBytes = base64ToByte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static String encrypt(String data, String publicKeyStr)
    {
        try {

            PublicKey publicKey = RSA.stringToPublicKey(publicKeyStr);
            //用公钥加密
            byte[] publicEncrypt = RSA.publicEncrypt(data.getBytes(), publicKey);
            //加密后的内容Base64编码
            return RSA.byteToBase64(publicEncrypt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String data, String privateKeyStr)
    {
        try{
            //将Base64编码后的私钥转换成PrivateKey对象
            PrivateKey privateKey = RSA.stringToPrivateKey(privateKeyStr);
            //加密后的内容Base64解码
            byte[] base64ToByte = RSA.base64ToByte(data);

            String s = new String(base64ToByte);
            //用私钥解密
            byte[] privateDecrypt = RSA.privateDecrypt(base64ToByte, privateKey);

            return new String(privateDecrypt);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //公钥加密
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    //私钥解密
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    //字节数组转Base64编码
    public static String byteToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    //Base64编码转字节数组
    public static byte[] base64ToByte(String base64Key) throws IOException {
        return Base64.getDecoder().decode(base64Key);
    }
    
    public static void main(String[] args) throws Exception {
//    	KeyPair kp = RSA.getKeyPair();
//    	String platformPrivateKey = RSA.getPrivateKey(kp);
//    	String publicKey = RSA.getPublicKey(kp);
//    	System.out.println(platformPrivateKey);
//    	System.out.println(publicKey);
////    	String platformPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC0Ptatf4wzOOJn2nqzJ0vPJ0ouVpSAgTNwCxm8rqvkU0l1uY7yGOYn1ZMcZI+/dwgYnnEOmEqhJY1/DxhcHuerWQ9Rg86gzoxYw27TaMf7VHIiP26hYCZmBSArDNmXypwfe8jfnnloOlS2UzdIBQcRoVTeWxiXNR8n3/k8Q9Oo3IpOzSYTfbXARCUvYc+rGJYLOnxoKLz8uu0mIHlPzAVpjl5Vx6MMe8mZS3CN6d2sSvVlgkMd7CnZTHuhFda2SC0bMtzLxegX8Om8Vi4lwr6vsPcMhNmgbx9QoDIqKu4wo7jkeRM7sHx0LHRyL0kPAe64FYWGnlHgxqpDTAuqHsEZAgMBAAECggEAIXiR0QIgfNqRYIu3wVEIyN0Y72XwibR0YtgM3XbqQKoeuBfPLbHl+3uzp9AcyYdHH5s8j2NEaIxarD+XXHP4vV76cz6wvjfc5BbWm7eXe1qBH078MkdymftVr+jg90kOmOIZuTzqeqpqVLk7SMp2N5Xn9qqf9GRO9s6mr8RAp9iMNDXypB+bzxgGVfnaCkGTh6o94OEDaz4SahMwWv7+cdOyiShvx5KPLeYVAcgbDzhtqGTtWQOBV0ad8v3tQAiDKG/cG3ztsCB2eu7lLKsFdUxSBkZMMsZpSCTdgQt/Yn8+rELDjxcEjbKXUFF/twlihtNzuxVUKCAG7bJv/cjIIQKBgQDuzoXG37FyX9v2usnYxFh0k+4kE3JgQBId1lTfGqkJzXesiBHeHAUrul01BLEuoLJ7MTN3Tg6cDAv6mzBDgp50cuCUeCsQx85v5Kq5ReQczc7t4BtlcTh1LLhYDIQlI0PnpElk4WCVJJp0Otk01ONsIYXtv3TXtxa8NN/rg4dNLQKBgQDBOPdAXPARFl23xioo4Ql24qrYaZpLe4tzovo1JmIvTBv84bcYiJVwTHTBaGIFg8KdGw2Xj7tXzwUTDvyVI01vb0CPL/L/FvUcao00mYX8nz9nptSumRezDCy6phuQgmbRjj07Subx2wKv15D6LIyeRCUZrlFrEip1p+jUYHbvHQKBgQCJfVYAHiIn25DlCL3Vk5Xp6JGfK6MaT0ke7JuIp+zSu/0+1EiVPESt3Gp1b/IjClo4GyeqdMJsjXMIIfkzpSxdlxY95MHGs4LjYR/584dWHhgZVIHxcbIzpD+uUeZzC065OxkWf7v/okday9bqV/dn9hUJwMUgvUTVrwuMqPlpMQKBgH/4h6EkLIdUpdtKLrAGYytO8lIElwE5IOIEZex/sHWc1ciFgauJAlhu+sEBrjHy0xkUSss/kZeVb8nwmg74Y5fTOhHbcvfJr6sTUtQsfdYTbn3qu1+r20tlW9Z+nzD1fL17lEhJ0m0KXVwyzD009ij3/w5kKl5cmKx1+bl27i5tAoGBALehAmndcskn72McS4HI6mwGF5pG9B1fLr/xGBzYmO9pdMLzkqlnjGdkarBUUlwpZGlLveUpWED5yEbY+EKU5i7MTwxDOvIaMsPfniMeOf2hCjss/A1FB+5B8CgbyW2SMECZ358TCJtGeKkFK2AAl6T87uJrSXSSgDueZ2sPICnE";
////		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxj5u/Yh42STc+1uAxib+3PM6vIN1I+9RnqWZZmtocdYMz1wTLM7RDUS2syXnfDqCWFnrSLuSVxNHx0cGmopjwWtcAE1n9EkEerCL1f1neOB5ijvYoRm5tCKZ2gm/GUeQxF86IsterIlsjkPFdjGhiDmua2Imm1bQ0IQHgnp0wMvfHQ4155SquQlrd6tHddiRnNZDoo3k6eV42Y8/+g0qcMSIkko0UuhS8SfcKXg8Pj7NUX9lTAn9YoCqx0ILbihkw6Wm55s5z0lmFMZUz7KTFiYE7WnG8/sGzmXWMOI1Pj5rs2dZEKr/7WLzwQNUak8boMGZZ6vIBNbHRnS3a5g+SQIDAQAB";
//		String enData = RSA.encrypt("123456", publicKey);
//		String data = RSA.decrypt(enData, platformPrivateKey);
//		System.out.println(data);
//		
		
			////CZD  解密返回值
			String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDFNNVJNnYZJbsXsWWBuPFDm8AlQRA39FoDnHiCkB1gGLVr2s+4r4YnUaeLisf6pSZ36ioDxlHg60mkmGgj4f6uGo5MoE3Ohz2wvCPilxSVb7Iknj1IX/ollMU/LAW4ziCqez5fyTOJKlbVtlvBnv2VQt4SejRXlkBTeKfD51WAjJ2LVXeewxNY4hxzymWl/2FlN2mn9TOMkqFPSI5Aj8W+mz271mdXJtaQzyqjNOWTkus09iM5ui+DYXGJWxZmiJ/LiHfnbPjQJ34rqGyzO5MNAkK24YmdYGRRbXZPctsHzgcZbFJzoY69FQt+vL+T5h0Si6dsE5bil61hgk/RnRDtAgMBAAECggEAVnT/PQW/aO79wzlmwySNwZI6/xozPhTGkea7fqIn/sVWZF6dyt5I9lv6FFdXrS9SWigzG0yLAQymUJ7wqSqfOT0zPazZ772i8iTJpsGNqKY7d4pYW66YQHmksDlgmNDlyVgW1G+lRHFneHJmb9CAZDvYmEkzQWMB+JZhFqYI+j3l/NVIcsiAzFpBbMnhwvAfwdRuGBM/Oq/5/kjeA0lqguhUJPs7BY7b9Oo8LGu0ZViGq8pQkiLPc7NjdWDLXJSXLBeDY65aLfbUEmb0vgvpWtcvwf/VSoP2agwd0t362jDBIMC0rTIXcsunOujzRyeXhNqsXrKMP1SlqWEIxPDwgQKBgQDziEJRzrdBZQX322N9BmW1kKfqysVI8Ggs9M5epcxEtQI5GcNJ6BBw2JXrhXc7ff2Vh+AokvGBcAqwRXGfayLMu6uUAoTk55OlXT8eZu/+Wj+PHtxoTP7/WomGi6IgGQ7U7/bd79JQQbEmlG0b1et8GfrqDx12pCQbrgpbaQ6DWQKBgQDPTWz75lkgWjCg/IUk4gQRPlZmxF63swSZXOkzc8dojNVdVCLeIXusAM1a/5c8zbm5AEu4duzgMr9vrd3W8o89cL5Hw4hC/Fk8sYQmcaPLYXYo8d8SwIcFif0QUCFwVlHAotTQcsjk+5gfu8y3KjD0nQXpgi3N+ISz9+iOmpRrtQKBgCKaWdIF7UP9druhEKHTJuCFjVZvOVkDz8mTfz8zj7KzhX1Xi8gUkUF3hNXSQP7TCBUXGWKqfmZF5T7mnx5nuqocgGSndvqPbQsyFKXdOnyJlj5fxDGNvy3JT4Eulfq5lMHs1sbyLI/6xZEVxesOmkcn6d8YBre02uyT7iTO6KEBAoGAbwhLXUhtaFSmQzO9pJmiEBJReM2o5b9usS4Agdx7a9Yy2cAcxNkuQlxToW/Qc3LFDP/Jjs2z5gmIW6rGbUCR1Bi08f8a1rYOneF+pJCtaFuVTCzwjDyulRrpfnlMz7yMIR+V2OgCDhNr0WNnLJveiMYpyn49RlrbyQD5gJfu8xkCgYA9AN7NBBL+T5huUwsavG4ljkDvuGYlRZotnejmtcFSPaBSVwELCe3hWSxoeDLh4c31Lp0MnVeDiAW7i4bi9MTY3fGMa1yFo9BBcwWW3r4kAGjjey3quSVGAlpF/sIxluDK7vyNtTA2ACCU8/XHksv/Q0l/YNO7/yNORnz3nffikQ==";
			String secret = "jevZzyy97UtpGkNr4tt5yKDeS2wEzJmetmR/gbRskPzqJzGYOKi72ohTmMkjrmU7N0uyJX1Dm8uKt39sWYTnx73Isvf6TIhyRP4kERbx2SpRSH0Lz09AA2QGve/q2wBUvX4SoCNmm3f1jIhDPUje/1H7cCijAG1h7X+5DcLYobSJGK0mrkTfXq+CYiFEmXmrNJXQn4PhKG5LM8jwSKMYy/mPayxKlRNZ7jYEZjDmM4iEpwikMvZGVrHojG61HXHNR7qdQz78KeVO1wv+8ol3wpwJx6TLpUBIpwvuAPU6MxT61ElsJM1wuCW2gg5688Ty8B9/97E/0vcY74ne4nYsmw==";
			String bankSecret = RSA.decrypt(secret, privateKey);
			String data1 = "udwM/DMdcQgb6l/1Z74jghwNMrqxWAf7tRVY+cRFDS9yyanpvFPv03RAnggqnVX64un6SeRLcEgOZjAfE5JgE7ONjdIXjHJ+LD5lOh50GUdsnkiVJxnQweJND/kKM2usL6hR7FBhmhfz5UYBktZKChj8sVQWwGjmxk52cJkiwuX8lmnrnGCasR0vb/6iaOPuBtZ5rlkxxr7F2FgOkeHbKUBZfVErwDiaUEF1+EeD/hKoqbHVb+wn+WjPnHWzGv25Z0FNsxe/JKl09RMFiPrruvdM0xzjrV5xg9F3ylkwxsc=";
			String deData = AES.decrypt(data1, bankSecret);
			System.out.println(deData);
	}
    
    
    
    

}