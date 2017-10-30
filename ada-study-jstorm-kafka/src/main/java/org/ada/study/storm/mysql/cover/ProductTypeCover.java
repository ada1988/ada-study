package org.ada.study.storm.mysql.cover;
/**  
 * Filename: ProductTypeCover.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

public class ProductTypeCover implements IFieldValueCovert{
	private String productType = null;
	public ProductTypeCover(String productType){
		this.productType = productType;
	}
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public String valueCover(String original) {
		return  productType;
	}

}
