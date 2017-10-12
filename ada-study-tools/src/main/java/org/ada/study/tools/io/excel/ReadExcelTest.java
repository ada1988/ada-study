package org.ada.study.tools.io.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;


public class ReadExcelTest {
	public static void main(String[] args){
		ReadExcelTest.readExcelDome();
	}
	static String basePath ="D:\\";
	public static void readExcelDome(){
		//读文件
		File file = new File(basePath+"insure-commssion.xlsx");
		FileInputStream fileIo = null;
		try {
			fileIo = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ExcelXXXLineHander lineOption = new ExcelXXXLineHander();
		ReadExcel<String> excelUtils = new ReadExcel<String>();
		List<String> commssions = excelUtils.readExcel(fileIo, lineOption, 0);
		Path path = Paths.get( "D://insure-commssion.txt" );
		try {
			Files.write( path, commssions, StandardOpenOption.CREATE );
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("结束");
	}
	
	
	static class ExcelXXXLineHander extends AbstractOptionEntity<String>{

		@Override
		public String optionRow(Row row) {
			//获取行数据中的所有列，拼装成实体放回
			String sql = "update tbl_product_commission_desc set description = '"+row.getCell(1).toString()+"' where product_code='"+row.getCell(0).toString()+"'; \r\n";
			return sql;
		}

	}
	
}
