package org.ada.study.sharing.jdbc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;

@Configuration
@MapperScan("org.ada.study.sharing.jdbc.mapper*")
public class MybatisPlusConfig {

	/**
	 * 性能监控插件 生产环境可关闭
	 * 
	 * @return
	 * @author: zhuzhao
	 * @Createtime: 2017年10月27日
	 */
	@Bean
	@ConditionalOnProperty(prefix = "config", name = "mybatis-plus-performance-monitor", havingValue = "true")
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

	/**
	 * 分页插件PageHelper
	 * 
	 * @return
	 * @author: zhuzhao
	 * @Createtime: 2017年10月27日
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
		return paginationInterceptor;
	}

	/**
	 * 乐观锁
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor(){
		return new OptimisticLockerInterceptor();
	}
}
