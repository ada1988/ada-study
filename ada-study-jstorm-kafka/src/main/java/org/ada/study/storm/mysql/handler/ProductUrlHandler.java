package org.ada.study.storm.mysql.handler;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ada.study.storm.mysql.cover.IFieldValueCovert;
import org.ada.study.storm.mysql.cover.PostParamCover;
import org.ada.study.storm.mysql.cover.ProductTypeCover;
import org.ada.study.storm.mysql.cover.UserMobileUrlCover;
import org.ada.study.storm.mysql.em.ProductFiledsDbEM;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.tuple.Tuple;

/**
 * Filename: InsureUrlHandler.java <br>
 *
 * Description: <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月30日 <br>
 *
 * 
 */

@SuppressWarnings("rawtypes")
public class ProductUrlHandler implements IUrlHandler<Tuple, List<Column>> {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	public Map<String, IFieldValueCovert>	paramCovers			= new HashMap<String, IFieldValueCovert>();
	public ProductUrlHandler(String productType,String paramName){
		/**
		 * 装载字段处理器
		 */
		for (ProductFiledsDbEM em : ProductFiledsDbEM.values()) {
			if (em == ProductFiledsDbEM.product_id)
				paramCovers.put( em.getFieldName(), new PostParamCover( paramName ) );
			if (em == ProductFiledsDbEM.user_mobile)
				paramCovers.put( em.getFieldName(), new UserMobileUrlCover() );
			if (em == ProductFiledsDbEM.product_type)
				paramCovers.put( em.getFieldName(), new ProductTypeCover( productType ) );
		}
	}

	@Override
	public List<Column> handler(Tuple req) {
		List<Column> columns = new ArrayList<Column>();

		IFieldValueCovert cover = null;
		String value = null;
		int[] preIndexs = null;
		for (ProductFiledsDbEM em : ProductFiledsDbEM.values()) {

			value = null;
			cover = paramCovers.get( em.getFieldName() );
			preIndexs = em.getPreIndexs();
			for (int preIndex : preIndexs) {
				if (null == value)
					value = req.getString( preIndex );
				else
					value = value + "###" + req.getString( preIndex );
			}
			if (null != cover) {
				value = cover.valueCover( value );
			}
			columns.add( new Column( em.getFieldName(), value, Types.VARCHAR ) );
		}
		return columns;
	}

}
