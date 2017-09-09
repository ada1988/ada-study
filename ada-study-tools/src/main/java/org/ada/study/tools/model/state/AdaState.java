package org.ada.study.tools.model.state;


/**  
 * Filename: State.java  <br>
 *
 * Description: 节点状态  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月13日 <br>
 *
 *  
 */

public interface AdaState {
	/**
	 * 具体业务的处理器
	 * @return
	 * @author: CZD  
	 * @Createtime: 2015年8月13日
	 */
    ResultBean handle(AdaContext context) throws Exception;
    /**
     * 是否进行下一级
     * @return true可以;false不可以
     * @author: CZD  
     * @Createtime: 2015年8月13日
     */
    boolean isPass();
    /**
     * 供子类使用：获取上一节点传递的对象【状态间传值】
     * @return 
     * @author: CZD  
     * @Createtime: 2015年8月13日
     */
    public Object getPreviousParam();
    /**
     * 供子类使用：设置传递到下一节点的对象【状态间传值】
     * @return 
     * @author: CZD  
     * @Createtime: 2015年8月13日
     */
    public void setNextParam(Object obj);
}
