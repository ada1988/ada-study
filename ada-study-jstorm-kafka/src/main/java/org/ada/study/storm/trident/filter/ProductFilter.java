package org.ada.study.storm.trident.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: ProductFilter.java  <br>
 *
 * Description: 产品过滤数据  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月26日 <br>
 *
 *  
 */

public class ProductFilter extends BaseFilter{

	private static final long	serialVersionUID	= 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductFilter.class);

	private String[] patterns = null;
	
	public ProductFilter(String[] patterns ){
		this.patterns = patterns;
	}
	
	@Override
	public boolean isKeep(TridentTuple tuple) {
		Object sentence = tuple.getValue(0);
		for(String reg:patterns){
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(null!=sentence?sentence.toString():""); // 操作的字符串
			if(matcher.find()){
				LOGGER.error("pass--------------->ProductFilter过滤器，通过该条数据:{}",sentence);
				return true;
			}
		}
		LOGGER.error("discard--------------->ProductFilter过滤器，未通过该条数据:{}",sentence);
		return false;
	}

}
