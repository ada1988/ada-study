package org.ada.study.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**  
 * Filename: StartMainByPath.java  <br>
 *
 * Description: 启动一个存在main方法的类  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月12日 <br>
 *
 *  
 */

public class StartMainClass {
	public static void main(String[] args) {
		try {
			Class<?> mainClass = Thread.currentThread().getContextClassLoader()
					.loadClass("org.ada.study.reflect.Test");
			Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
			mainMethod.invoke(null, new Object[] { args });
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
