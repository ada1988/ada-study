package org.ada.study.tools.model.state.impl;

import org.ada.study.tools.model.state.AdaContext;
import org.ada.study.tools.model.state.AdaState;
import org.ada.study.tools.model.state.ResultBean;

/**  
 * Filename: RedsState.java  <br>
 *
 * Description: 第三个节点  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月13日 <br>
 *
 *  
 */

public class BlacksState implements AdaState{
	//前一个状态设置的值
	private Object obj = null;
	//是否通过
	private boolean isPass = true;

	@Override
	public ResultBean handle(AdaContext context) {
		System.out.println("===========当前节点【BlacksState】");
		ResultBean bean = new ResultBean();
		isPass = false;
		//设置下一个节点
		context.setState(null);
		return bean;
	}

	@Override
	public boolean isPass() {
		return isPass;
	}

	@Override
	public void setNextParam(Object obj) {
		System.out.println("当前节点、状态[BlacksState]    【由上一节点、状态】传递的值："+(obj==null?"":obj.toString()));
	}
	@Override
	public Object getPreviousParam() {
		return obj;
	}
}
