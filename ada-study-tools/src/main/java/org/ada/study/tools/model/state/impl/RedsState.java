package org.ada.study.tools.model.state.impl;

import org.ada.study.tools.model.state.AdaContext;
import org.ada.study.tools.model.state.AdaState;
import org.ada.study.tools.model.state.ResultBean;

/**  
 * Filename: RedsState.java  <br>
 *
 * Description:  第一个个节点 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月13日 <br>
 *
 *  
 */

public class RedsState implements AdaState{
	//是否通过
	private boolean isPass = false;
	//前一个状态设置的值
	private Object obj = null;
	
	@Override
	public ResultBean handle(AdaContext context) {
		System.out.println("===========当前节点【RedsState】");
		ResultBean bean = new ResultBean();
		bean.setMgs("RedsState");
		isPass = true;
		//设置下一个节点
		context.setState(new BluesState());
		return bean;
	}

	@Override
	public boolean isPass() {
		return isPass;
	}

	public void setNextParam(Object obj) {
		this.obj = obj;
		System.out.println("当前节点、状态[RedsState] 状态传递的值:"+(obj==null?"":obj.toString()));
	}

	@Override
	public Object getPreviousParam() {
		String obj = ":RedsState 给你给耳光";
		return obj;
	}

}
