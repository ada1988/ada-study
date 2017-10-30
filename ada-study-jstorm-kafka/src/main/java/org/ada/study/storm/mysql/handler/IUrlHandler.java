package org.ada.study.storm.mysql.handler;

import java.io.Serializable;

/**  
 * Filename: IUrlHandler1.java  <br>
 *
 * Description:  单独请求处理 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

public interface IUrlHandler<Req,Res>  extends Serializable{
	/**
	 * 单独处理某一个url对应的数据
	 * @param req
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年10月30日
	 */
	public Res handler(Req req);
}
