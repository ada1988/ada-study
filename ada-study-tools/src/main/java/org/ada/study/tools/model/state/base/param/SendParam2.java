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

public class SendParam2 extends BaseState{
	String state_name = "SendParam2";
	@Override
	public AdaState next() {
		return new SendParam3();
	}

	@Override
	public void work() {
		//获取上一节点参数
		String param = getPreviousParam().toString();
		System.out.println(state_name+":谁啊？礼品["+param+"],哈哈，有银子，进去吧，也太少了，滚!");
		//终止
		isPass = false;
	}

}
