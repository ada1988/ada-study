package org.ada.study.tools.api;

import org.ada.study.tools.api.dock.ThridProductListAPI;
import org.ada.study.tools.api.model.RespBase;
import org.ada.study.tools.api.model.product.ProductReq;
import org.ada.study.tools.api.model.product.ProductRespData;

/**  
 * Filename: Test.java  <br>
 *
 * Description:   测试 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月16日 <br>
 *
 *  
 */

public class Test {
	public static void main(String[] args) {
		ProductReq req = new ProductReq();
		try {
			RespBase<ProductRespData> resp = new ThridProductListAPI().requestAPI( req );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
