package org.ada.study.algorithm;
/**  
 * Filename: Dichotomy.java  <br>
 *
 * Description:  算法 <br>
 * 二分法
 * 冒泡法
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月27日 <br>
 *
 *  
 */

public class Dichotomy {
	/**
	 * 二分法查找
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月27日
	 */
	public static int search1(int[] temp,int s){
		int result = -1;
		int start = 0;
		int end = temp.length -1;
		while(start <= end){
			int mid = (start+end)/2;
			if(temp[mid]>s) end = mid -1;
			else if(temp[mid]<s) start = mid +1;
			else if(temp[mid] == s) {result = mid ;break;}
		}
		return result;
	}
	
	/**
	 * 冒泡算法
	 * @param desc
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月27日
	 */
	public static int[] order(int[] desc){
		for(int i=0;i<desc.length;i++){
			for(int j=i+1;j<desc.length;j++){
				System.out.println(i+":"+j);
				int temp = desc[i];
				if(temp < desc[j] ) 
				{
					desc[i] = desc[j];desc[j] = temp;
				}
			}
		}
		return desc;
	}
	
	public static void main(String[] args) {
		/*int index = Dichotomy.search1( new int[]{0,3,7,9,23,42,58},42 );
		System.out.println(index);*/
		int[] desc = Dichotomy.order( new int[]{0,3,17,9,30,4,58} );
		for(int d:desc){
			System.out.println(d);
		}
	}
}
