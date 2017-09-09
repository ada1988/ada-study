package org.ada.study.tools.model.state.base.param;

import org.ada.study.tools.model.state.AdaState;
import org.ada.study.tools.model.state.base.BaseState;

/**  
 * Filename: SendParam1.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年12月31日 <br>
 *
 *  
 */

public class SendParam1 extends BaseState{
	String state_name = "SendParam1";
	@Override
	public AdaState next() {
		return new SendParam2();
	}

	@Override
	public void work() {
		String param = "一两白银";
		//向下一节点，设置参数
		setNextParam(param);
		System.out.println("["+state_name+"],要去SendParam2【李员外】家送礼，礼品["+param+"]。");
	}

}
