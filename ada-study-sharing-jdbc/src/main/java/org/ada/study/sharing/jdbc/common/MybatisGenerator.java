package org.ada.study.sharing.jdbc.common;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 自动生成Mybatis映射文件
 * Filename: MybatisGenerator.java  <br>
 *
 * Description:   <br>
 * 
 * @author: zhuzhao <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月24日 <br>
 *
 * @Copyright: Copyright (c)2016 by zhuzhao <br>
 */
public class MybatisGenerator {
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 输出目录
		String outputDir = "C:\\Users\\Administrator\\Desktop\\mybatisgenerator";
		// 父包地址
		String parentPath = "org.ada.study.sharing.jdbc";
		String entityPath = "model";
		String mapperPath = "mapper";
		String xmlPath = "sqlmap";
		String driverName = "com.mysql.jdbc.Driver";
		/*String username = "exiangjia";
		String password = "exj@muyu!123321";
		String url = "jdbc:mysql://192.168.10.110:3306/exj_creater?characterEncoding=utf8";*/
		String username = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/order_db?characterEncoding=utf8";
		DbType dialect = DbType.MYSQL;
		NamingStrategy naming = NamingStrategy.underline_to_camel;

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(outputDir);
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setServiceName("%sService");
		gc.setAuthor("czd");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(dialect);
		dsc.setDriverName(driverName);
		dsc.setUsername(username);
		dsc.setPassword(password);
		dsc.setUrl(url);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(naming);// 表名生成策略
		mpg.setStrategy(strategy);
		
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(parentPath);
		pc.setEntity(entityPath);
		pc.setMapper(mapperPath);
		pc.setXml(xmlPath);
		pc.setController("");
		mpg.setPackageInfo(pc);

		// 执行生成
		mpg.execute();
	}
}
