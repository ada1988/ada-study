package org.ada.study.tools.model.state.base.impl;

import org.ada.study.tools.model.state.AdaState;
import org.ada.study.tools.model.state.base.BaseState;

/**  
 * Filename: CategoryState.java  <br>
 *
 * Description: 状态模式：替换if判断  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月14日 <br>
 *
 *  
 */

public class CategorySaveState1 extends BaseState{

	@Override
	public AdaState next() {
		return new CategorySaveState2();
	}

	@Override
	public void work() {
		System.out.println("CategorySaveState1");
	}

}
