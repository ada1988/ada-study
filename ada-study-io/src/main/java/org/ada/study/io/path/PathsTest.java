package org.ada.study.io.path;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**  
 * Filename: PathsTest.java  <br>
 *
 * Description:  jdk1.7 新增工具 <br>
 * 
 * Path 目录名序列
 * Paths 获取文件路径：相对或绝对，并相互转化
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月20日 <br>
 *
 *  
 */

public class PathsTest {
	public static void main(String[] args) throws IOException{
		System.out.println(Paths.get( "d:/" ));
		System.out.println(Paths.get( "d:/aa.txt" ).toFile());//Path 转File
		System.out.println(new File(Files.createTempFile( null, ".txt" ).toString()).toPath());// File转Path
	}
}
