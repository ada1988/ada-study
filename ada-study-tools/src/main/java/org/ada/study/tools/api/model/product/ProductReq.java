package org.ada.study.tools.api.model.product;

import org.ada.study.tools.api.model.ReqBase;

/**  
 * Filename: ProductReq.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月16日 <br>
 *
 *  
 */

public class ProductReq extends ReqBase{
	private String productCode;//通过编码获取产品

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
