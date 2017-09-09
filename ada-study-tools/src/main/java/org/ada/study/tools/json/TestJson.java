package org.ada.study.tools.json;

import java.awt.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;

/**  
 * Filename: TestJson.java  <br>
 *
 * Description: 测试内存溢出  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月23日 <br>
 *
 *  
 */

public class TestJson {
	public static void testJson2List(){
		for(int j=0;j<2000000;j++){
			StringBuffer json = new StringBuffer("[");
			for(int i=0;i<new Random().nextInt( 200 );i++){
				if(i==0){
					json.append( "{name").append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) )
					.append( "," ).append( "content" ).append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) ).append( "}" );
				}else{
					json.append( ",{name").append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) )
					.append( "," ).append( "content" ).append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) ).append( "}" );
				}
			}
			json.append( "]" );
			System.out.println(json.toString());
			Object body = JSONArray.parseArray( json.toString(), List.class );
			//java.util.List<List> list = JSON.parseArray( json.toString(), List.class );
		}
	}
	public static void testJson2Map(){
		for(int j=0;j<2000000;j++){
			StringBuffer json = new StringBuffer("{");
			for(int i=0;i<new Random().nextInt( 200 );i++){
				if(i==0){
					json.append( "name").append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) )
					.append( "," ).append( "content" ).append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) ).append( "" );
				}else{
					json.append( ",name").append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) )
					.append( "," ).append( "content" ).append( new Random().nextInt( 20000 ) ).append( ":" ).append( new Random().nextInt( 20000 ) ).append( "" );
				}
			}
			json.append( "}" );
			System.out.println(json.toString());
			Object body = JSONArray.parseObject( json.toString(), Map.class );
			//java.util.List<List> list = JSON.parseArray( json.toString(), List.class );
		}
	}
	public static void main(String[] args) {
		try {
			TestJson.testJson2Map();
			System.out.println("结束....");
			Thread.currentThread().wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
