package org.ada.study.tools.api.dock;

import org.ada.study.tools.api.common.APIDockBase;
import org.ada.study.tools.api.common.GlobalContants;
import org.ada.study.tools.api.model.RespBase;
import org.ada.study.tools.api.model.product.ProductReq;
import org.ada.study.tools.api.model.product.ProductRespData;

/**  
 * Filename: ThridProductListAPI.java  <br>
 *
 * Description: 测试接口  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月16日 <br>
 *
 *  
 */

public class ThridProductListAPI extends APIDockBase<ProductReq, ProductRespData>{

	@Override
	public Class<ProductRespData> respDataClass() {
		return ProductRespData.class;
	}

	@Override
	public String apiUrl() {
		return GlobalContants.API_URL_PRODUCT_LIST;
	}

	@Override
	public RespBase<ProductRespData> requestAPI(ProductReq req) throws Exception {
		return super.requestBaseApi( req );
	}

}
