package org.ada.study.tools.model.state.base.impl;

import org.ada.study.tools.model.state.AdaContext;
import org.ada.study.tools.model.state.AdaState;
import org.ada.study.tools.model.state.ResultBean;
import org.ada.study.tools.model.state.base.BaseState;

/**  
 * Filename: CategorySaveState4.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月14日 <br>
 *
 *  
 */

public class CategorySaveState4 extends BaseState{
	@Override
	public AdaState next() {
		return null;
	}

	@Override
	public void work() {
		System.out.println("CategorySaveState4");
	}
	
	public static void main(String[] args) {
		AdaContext context = new AdaContext();
		context.setState(new CategorySaveState1());
		ResultBean bean = context.work();
		System.out.println("终极返回结果："+bean);
	}
}
