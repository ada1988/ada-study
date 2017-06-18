package org.ada.study.cache.spring;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**  
 * Filename: AdaKeyGenerator.java  <br>
 *
 * Description:  自定义缓存键生成器 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月18日 <br>
 *
 *  
 */
@Component("adaKeyGenerator")
public class AdaKeyGenerator implements KeyGenerator{
	 @Override  
     public Object generate(Object o, Method method, Object... objects) {  
         StringBuilder sb = new StringBuilder();  
         sb.append(o.getClass().getName());  
         sb.append(method.getName());  
         for (Object obj : objects) {  
             sb.append(obj.toString());  
         }  
         return sb.toString();  
     } 
}
