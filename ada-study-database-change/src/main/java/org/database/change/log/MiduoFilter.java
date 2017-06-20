package org.database.change.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Filename: MiduoFilter.java <br>
 *
 * Description: <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年4月25日 <br>
 *
 * 
 */

public class MiduoFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (event.getMessage().contains( "miduo" )) {
			return FilterReply.ACCEPT; // 允许输入串
		} else {
			return FilterReply.DENY; // 不允许输出
		}
	}
}