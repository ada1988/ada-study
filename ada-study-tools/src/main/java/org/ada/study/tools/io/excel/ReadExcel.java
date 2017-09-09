package org.ada.study.tools.io.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**  
 * Filename: ReanExcel.java  <br>
 *
 * Description: 读取excel  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2016年4月19日 <br>
 *
 *  
 */

public class ReadExcel<T> {
	XSSFWorkbook wb = null;
	public void flushWb(){
		wb = null;
	}
	/**
	 * 
	 * @param inputStream
	 * @param option
	 * @return 2010Excel
	 * @author: CZD  
	 * @Createtime: 2016年4月19日
	 */
	public List<T> readExcel(InputStream inputStream,OptionEntity<T> option,int sheetNum){
		List<T> datas = null;
		T t = null;
		try {
			datas = new ArrayList<T>();
			if(null==wb){
				wb = new XSSFWorkbook(inputStream);
			}
			Sheet sheet = wb.getSheetAt(sheetNum);
			Row row = null; 
			for(int i = 1;i<=sheet.getLastRowNum();i++){
				row = sheet.getRow(i);
				 t =  option.option(row);
				if(null==t)
				    continue;
				datas.add(t);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return datas;
	}
}
