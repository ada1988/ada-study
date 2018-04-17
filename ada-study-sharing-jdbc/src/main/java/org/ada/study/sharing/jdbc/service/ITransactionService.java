package org.ada.study.sharing.jdbc.service;
/**  
 * Filename: ITransactionService.java  <br>
 *
 * Description: 事物测试  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年4月17日 <br>
 *
 *  
 */

public interface ITransactionService {
	public void insert0();
	/**
	 * 全部回滚
	 * 
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	public void insert1();
	/**
	 * 还是全部回滚
	 * 
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	public void insert2();
	public void insert3();
}
