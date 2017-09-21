package org.ada.study.tools.task.common;
/**  
 * Filename: IRedisCacheTemplate.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月21日 <br>
 *
 *  
 */

public interface IRedisCacheTemplate {
	/**
	 * 检查key是否存在
	 * @param key
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年9月21日
	 */
	public boolean exists(String key);
	
	/**
	 * 设置值（过期时间）
	 * @param key
	 * @param value
	 * @param expire
	 * @author: CZD  
	 * @Createtime: 2017年9月21日
	 */
	public void setValue(String key,Object value,Integer expire);
	
	/**
	 * 删除key
	 * @param key
	 * @author: CZD  
	 * @Createtime: 2017年9月21日
	 */
	public void deleteValue(String key);
}
