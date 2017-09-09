package org.ada.study.tools.io.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;


public class Test {
	public static void main(String[] args){
		
	}
	String basePath ="D:\\import\\product\\";
	public void readExcelDome(){
		//读文件
		File file = new File(basePath+"tbl_product.xlsx");
		FileInputStream fileIo = null;
		try {
			fileIo = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ExcelXXXLineHander lineOption = new ExcelXXXLineHander();
		ReadExcel<UserInfo> excelUtils = new ReadExcel<UserInfo>();
		List<UserInfo> users = excelUtils.readExcel(fileIo, lineOption, 0);
	}
	
	class UserInfo{
		private String id;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	class ExcelXXXLineHander extends AbstractOptionEntity<UserInfo>{

		@Override
		public UserInfo optionRow(Row row) {
			//获取行数据中的所有列，拼装成实体放回
			UserInfo user = new UserInfo();
			user.setId(row.getCell(0).toString());
			user.setName(row.getCell(1).toString());
			return user;
		}

	}
	
}
