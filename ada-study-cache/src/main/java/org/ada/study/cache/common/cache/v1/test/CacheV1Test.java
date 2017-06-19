package org.ada.study.cache.common.cache.v1.test;

import java.util.ArrayList;
import java.util.List;

import org.ada.study.cache.common.cache.v1.AdaCache;
import org.ada.study.cache.common.cache.v1.QueryFunction;

/**  
 * Filename: CacheV1Test.java  <br>
 *
 * Description:  第一版缓存测试 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月19日 <br>
 *
 *  
 */

public class CacheV1Test {
	public static void main(String[] args) {
		CacheV1Test test =new CacheV1Test();
		test.testCacheV1();
		test.testCacheV1();
		test.testCacheV1();
		test.testCacheV1();
	}
	
	/**
	 * 测试V1版缓存
	 * 
	 * @author: CZD  
	 * @Createtime: 2017年6月19日
	 */
	public void testCacheV1(){
		List<UserInfo> users = AdaCache.getCache( "user.list.all", new UserListMissing() );
		for(UserInfo user:users){
			System.out.println(user.toString());
		}
	}
	
	/**
	 * 缓存丢失后，获取的实时数据
	 */
	private class UserListMissing implements QueryFunction<List<UserInfo>>{

		@Override
		public List<UserInfo> missData() {
			List<UserInfo> list = new ArrayList<UserInfo>();
			for(int i=10;i>0;i--){
				list.add( new UserInfo( i, "name"+i ) );
			}
			System.out.println("获取实时数据....");
			return list;
		}
		
	}
}
