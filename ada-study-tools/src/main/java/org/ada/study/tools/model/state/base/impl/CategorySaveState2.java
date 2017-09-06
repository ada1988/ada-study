package org.ada.study.tools.model.state.base.impl;

import org.ada.study.tools.model.state.AdaState;
import org.ada.study.tools.model.state.base.BaseState;

/**  
 * Filename: CategorySaveState2.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月14日 <br>
 *
 *  
 */

public class CategorySaveState2 extends BaseState{

	@Override
	public AdaState next() {
		return new CategorySaveState3();
	}

	@Override
	public void work() {
		System.out.println("CategorySaveState2");
	}

}
