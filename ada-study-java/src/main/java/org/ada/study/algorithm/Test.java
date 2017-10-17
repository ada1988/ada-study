package org.ada.study.algorithm;
/**  
 * Filename: Test.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月27日 <br>
 *
 *  
 */

public class Test {
	public static void test3(int desc){
		System.out.println("%:"+desc/10);
	}
	public static void test(int desc){
		System.out.println("/:"+desc/10);
	}
	public static void test2(int desc){
		System.out.println("&:"+(desc&10));
	}
	public static void main(String[] args) {
		/*for(int i=0;i<100;i++){
			System.out.println("========================================");
			test( i );
			test2( i );
			test3( i );
			System.out.println("========================================");
		}*/
		System.out.println(100&-25);
		System.out.println(43&-25);
		System.out.println(54&25);
	}
}
