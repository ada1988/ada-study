package org.ada.study.tools.model.state.test;

import org.ada.study.tools.model.state.AdaContext;
import org.ada.study.tools.model.state.ResultBean;
import org.ada.study.tools.model.state.base.impl.CategorySaveState1;
import org.ada.study.tools.model.state.base.param.SendParam1;
import org.ada.study.tools.model.state.impl.RedsState;

/**  
 * Filename: StateTest.java  <br>
 *
 * Description:   状态模式测试<br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月13日 <br>
 *
 *  
 */

public class StateTest {
	/**
	 * 基本流程【封装后】
	 * 
	 * @author: CZD  
	 * @Createtime: 2015年12月31日
	 */
	public static void testBaseStateSendParam(){
		AdaContext context = new AdaContext();
		context.setState(new SendParam1());
		ResultBean bean = context.work();
		System.out.println("终极返回结果："+bean);
	}
	/**
	 * 基本流程【封装后】
	 * 
	 * @author: CZD  
	 * @Createtime: 2015年12月31日
	 */
	public static void testBaseState(){
		AdaContext context = new AdaContext();
		context.setState(new CategorySaveState1());
		ResultBean bean = context.work();
		System.out.println("终极返回结果："+bean);
	}
	/**
	 * 基本流程[简单模式]
	 * 
	 * @author: CZD  
	 * @Createtime: 2015年12月31日
	 */
	public static void testNormalState(){
		AdaContext context = new AdaContext();
		context.setState(new RedsState());
		ResultBean bean = context.work();
		System.out.println("终极返回结果："+bean);
	}
	
	public static void main(String[] args) {
		testBaseStateSendParam();
	}
}
