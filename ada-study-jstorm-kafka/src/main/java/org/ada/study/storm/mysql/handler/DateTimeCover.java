package org.ada.study.storm.mysql.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Filename: DateTimeCover.java  <br>
 *
 * Description:  日期格式转换 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月27日 <br>
 *
 *  
 */

public class DateTimeCover implements IFieldValueCovert{
	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeCover.class);
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public String valueCover(String original) {
		return parseTime( original ).toString();
	}

	
	public static Long parseTime(String datdString) {
        try {
        	datdString = datdString.replace("+0800", "").replaceAll("\\(.*\\)", "");
            //将字符串转化为date类型，格式2016-10-12
            SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
            Date dateTrans = null;
            dateTrans = format.parse(datdString);
            return dateTrans.getTime()/1000;
        } catch (ParseException e) {
        	LOGGER.error( "强制转换日期时，数据错误：{}"+ datdString);
        	 return 0L;
        }
    }
	
	public static String parseStrTime(String datdString) {
        datdString = datdString.replace("+0800", "").replaceAll("\\(.*\\)", "");
        //将字符串转化为date类型，格式2016-10-12
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        Date dateTrans = null;
        try {
            dateTrans = format.parse(datdString);
            return new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(dateTrans).replace("-","/");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datdString;
    }

	public static void main(String[] args) {
		String s = "27/Oct/2017:14:05:22 +0800";
		System.out.println(parseTime( s ));
	}
}
