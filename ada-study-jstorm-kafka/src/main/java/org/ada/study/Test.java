package org.ada.study;

import java.util.Arrays;
import java.util.List;

import clojure.main;

/**  
 * Filename: Test.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月26日 <br>
 *
 *  
 */

public class Test {
	public static void fields(String... fields) {
		List<String> t = Arrays.asList(fields);
       System.out.println(t);
    }
	
	public void testSplit(){
		String s = "192.168.4.2 ||| - ||| [25/Oct/2017:18:31:00 +0800] ||| GET /indexStocks.htm?callback=jQuery172044096804856714944_1508893519221&_=1508927599608 HTTP/1.1 200 450 ||| https://www.miduo.com/product/list_0/0.htm ||| - ||| Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36 ||| - ||| _cus_sessionid=175f27d499cd4ab19a650875a01c1a68; MDSESSION=316f4058-193d-4295-b5ee-73e5ea145192; md_mobile=; JSESSIONID=65052091A600448F397885B79FFB5CB0; clientlanguage=zh_CN; Hm_lvt_106ad424edc9204588d8943ba56faeb6=1507521096,1507526981; Hm_lpvt_106ad424edc9204588d8943ba56faeb6=1508893522 |||recv_session_id=[316f4058-193d-4295-b5ee-73e5ea145192; md_mobile=; JSESSIONID=65052091A600448F397885B79FFB5CB0; clientlanguage=zh_CN; Hm_lvt_106ad424edc9204588d8943ba56faeb6=1507521096,1507526981; Hm_lpvt_106ad424edc9204588d8943ba56faeb6=1508893522] ||| sent_session_id=[316f4058-193d-4295-b5ee-73e5ea145192; md_mobile=; JSESSIONID=65052091A600448F397885B79FFB5CB0; clientlanguage=zh_CN; Hm_lvt_106ad424edc9204588d8943ba56faeb6=1507521096,1507526981; Hm_lpvt_106ad424edc9204588d8943ba56faeb6=1508893522]";
		for(String v:s.split( " \\|\\|\\| " )){
			System.out.println(v);
		}
		
	}
	
	public static void main(String[] args) {
		String[] fields = new String[]{"1","2","3"};
		Test.fields(fields);
	}
}
