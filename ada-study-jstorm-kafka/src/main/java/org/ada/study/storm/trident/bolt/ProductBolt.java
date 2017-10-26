package org.ada.study.storm.trident.bolt;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filename: ProductBolt.java <br>
 *
 * Description: 产品处理器 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年10月26日 <br>
 *
 * 
 */

public class ProductBolt extends BaseFunction {

	private static final long	serialVersionUID	= 1L;

	private static final Logger	LOGGER				= LoggerFactory.getLogger( ProductBolt.class );

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		Object sentenceO = tuple.getValue( 0 );
		String sentence = null == sentenceO ? "" : sentenceO.toString();
		LOGGER.error( "ProductBolt处理器，对数据进行处理={}", sentence );
		collector.emit( new Values( sentence ) );
	}
}
