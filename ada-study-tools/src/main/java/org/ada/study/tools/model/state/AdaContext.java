package org.ada.study.tools.model.state;


/**  
 * Filename: Context.java  <br>
 *
 * Description: 状态模式上下文控制  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月13日 <br>
 *
 *  
 */

public class AdaContext {
	//当前节点
	private AdaState state = null;  
	
	private ResultBean bean = null;

	public AdaState getCurrentSate() {  
		return state;  
	}  

	public void setState(AdaState state) {  
		this.state = state;  
	}  
	
	public ResultBean work(){
		boolean isPass = true;
		Object obj = null;
		while(this.getCurrentSate() != null&&isPass){
			AdaState current = this.getCurrentSate();
			//首次执行
			try {
				bean = current.handle(this);
			} catch (Exception e) {
				bean = new ResultBean(MsgHelp.ResultCodes.ADA_ERROR.toString(),MsgHelp.ADA_IN_MODE_ERROR+e.getMessage(),null);
				bean.setFail(false);
				break;
			}
			//是否进行下一阶段
			isPass = current.isPass();
			//获取要传递的值
			obj = current.getPreviousParam();
			//设置返回的值
			if(null!=this.getCurrentSate()){
				this.getCurrentSate().setNextParam(obj);
			}
		}
		return bean;
	}
	
}
