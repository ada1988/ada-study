package org.ada.study.tools.model.state.base;
import org.ada.study.tools.model.state.AdaContext;
import org.ada.study.tools.model.state.AdaState;
import org.ada.study.tools.model.state.MsgHelp;
import org.ada.study.tools.model.state.ResultBean;

/**  
 * Filename: SaveCategoryState.java  <br>
 *
 * Description: 状态模式——基类：替换if判断 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月14日 <br>
 *
 *  
 */

public abstract class BaseState implements AdaState{
	//是否通过,默认不执行下一节点
	public boolean isPass = true;
	//前一个状态设置的值
	private Object obj = null;
	//默认返回值
	Object[] result = new Object[]
			{
			isPass,
			new ResultBean(MsgHelp.ResultCodes.ADA_SUCCESS.toString(),MsgHelp.ADA_SUCCESS,null)
			};
	/**
	 * 具体业务的处理器
	 */
	public ResultBean handle(AdaContext context) {
		//执行具体业务
		work();
		//执行业务,并设置是否执行下一节点
		isPass = (Boolean)result[0];
		//节点返回值
		ResultBean bean = (ResultBean)result[1];
		//设置下一个节点
		context.setState(next());
		//如果中断：表示执行失败
		if(next()!=null&&!isPass){
			bean.setFail(true);
		}
		return bean;
	}
	/**
	 * 子类实现：设置下一节点
	 * @return
	 * @author: CZD  
	 * @Createtime: 2015年8月14日
	 */
	public abstract AdaState next();
	/**
	 * 子类实现：具体业务
	 * @return
	 * @author: CZD  
	 * @Createtime: 2015年8月14日
	 */
	public abstract void work();
	/**
	 * 子类可复写：设置自定义返回值
	 * @param result 注意：数组下标0必须是[boolean],标示是否进行下一节点;数组下标1必须是[ResultBean],标示返回信息;
	 * @author: CZD  
	 * @Createtime: 2015年8月14日
	 */
	public void setResult(Object[] result){
		this.result = result;
	}
    /**
     * 是否进行下一节点
     */
	public boolean isPass() {
		return isPass;
	}

	/**
	 * 供子类使用：获取上一节点传递的对象【状态间传值】
	 * @param result 
	 * @author: CZD  
	 * @Createtime: 2015年8月14日
	 */
	public Object getPreviousParam() {
		return obj;
	}

	/**
	 * 供子类使用：设置传递到下一节点的对象【状态间传值】
	 * @param result 
	 * @author: CZD  
	 * @Createtime: 2015年8月14日
	 */
	public void setNextParam(Object obj) {
		this.obj = obj;
	}

}
