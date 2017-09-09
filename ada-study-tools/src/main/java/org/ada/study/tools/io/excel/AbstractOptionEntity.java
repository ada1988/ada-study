package org.ada.study.tools.io.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public abstract class AbstractOptionEntity<Q> implements OptionEntity<Q>{

	@Override
	public Q option(Row row) {
		return optionRow(row);
	}

	public abstract Q optionRow(Row row);
	
	/**
	 * CELL_TYPE_NUMERIC 数值型 0;CELL_TYPE_STRING 字符串型 1;
	 * CELL_TYPE_FORMULA 公式型 2;CELL_TYPE_BLANK 空值 3;
	 * CELL_TYPE_BOOLEAN 布尔型 4;CELL_TYPE_ERROR 错误 5
	 * 
	 * @param cell
	 * @return
	 * @author: CZD  
	 * @Createtime: 2016年4月19日
	 */
	public Object cellValue(Cell cell){
		Object value = null;
		if(null!=cell){

	    	switch (cell.getCellType()) {
			case 0:
				 /* if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell))// 判断单元格是否属于日期格式  
				  {
					  value = cell.getDateCellValue().getTime()/1000;//java.util.Date类型  
				  }
				  else
				  {
					  //int temp = (int)cell.getNumericCellValue();
					  //if(temp>1){
						  value =  cell.getNumericCellValue();
						  if(null!=value){
							  String valueStr = value.toString();
							  if(valueStr.endsWith(".0")){
								  value = valueStr.substring(0,valueStr.indexOf(".0"));
							  }
						  }
					  //}else{
						//  value =  (double)cell.getNumericCellValue();
					  //}
				  }*/
				 value = String.format("%.0f",cell.getNumericCellValue());
				 if(cell.getNumericCellValue()==new Double(value.toString())){
					 value = String.format("%.0f",cell.getNumericCellValue());
				 }else{
					 value =  cell.getNumericCellValue();
				 }
				 
				break;
			case 1:
				value = cell.getStringCellValue();
				
				break;
			case 2:
				value = cell.getCellFormula();
				break;
			case 3:
				value = "";
				break;
			case 4:
				value = cell.getBooleanCellValue();
				break;
			case 5:
				value = "错误";
				break;
			default:
				break;
			}
		}
		
    	return value;
	}
}
