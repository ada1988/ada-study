package org.ada.study.tools.model.single;
/**  
 * Filename: SingleStatic.java  <br>
 *
 * Description: 单例模式  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月10日 <br>
 *
 *  
 */

public class SingleStatic {
	private final static SingleStatic single = Builder.single;
	
	private SingleStatic(){}
	
	final static class Builder{
		public final static SingleStatic single = new SingleStatic();
	}
	
	public static SingleStatic getInstance(){
		return single;
	}
	
	
	public static void main(String[] args) {
		SingleStatic s1 = SingleStatic.getInstance();
		System.out.println(s1);
	}
}
