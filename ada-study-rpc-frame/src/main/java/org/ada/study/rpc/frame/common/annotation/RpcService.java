package org.ada.study.rpc.frame.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**  
 * Filename: RpcService.java  <br>
 *
 * Description:  RPC 服务注解 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月17日 <br>
 *
 *  
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component // 表明可被 Spring 扫描
public @interface RpcService {
	Class<?> value();
}
