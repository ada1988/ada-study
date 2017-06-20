package org.ada.study.io.file;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**  
 * Filename: FilesTest.java  <br>
 *
 * Description:  jdk1.7 新增工具 <br>
 * 
 * Files 好多工具：创建目录、临时文件，写入文件、读取文件、行读取、行写入等
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月20日 <br>
 *
 *  
 */

public class FilesTest {
	
	public static void main(String[] args)  throws IOException{
		FilesTest.fileSystemTest();
	}
	
	/**
	 * 迭代所有文件构成的数组
	 * @throws IOException
	 * @author: CZD  
	 * @Createtime: 2017年6月21日
	 */
	public static void FilesEachFor() throws IOException{
		try(DirectoryStream<Path> entries = Files.newDirectoryStream( Paths.get( "D:\\java_source\\jdk_home\\java7\\jdk1.7.0_79\\jre\\lib" ) ))
		{
			for(Path entry:entries) System.out.println(entry);
		}
	}
	
	static String path = "D:\\java_source\\jdk_home\\java7\\jdk1.7.0_79\\jre\\lib\\jfr.jar";
	/**
	 * 文件系统 zip\jar 中遍历、查找文件
	 * 
	 * @author: CZD  
	 * @throws IOException 
	 * @Createtime: 2017年6月20日
	 */
	public static void fileSystemTest() throws IOException{
		FileSystem fs = FileSystems.newFileSystem( Paths.get( path ), null );
		//查找
		Path path = fs.getPath( "FlightRecording.class" );
		System.out.println("查找文件："+path);
		
		
		//遍历
		Files.walkFileTree( fs.getPath( "/" ), new SimpleFileVisitor<Path>(){
			//其中可以实现对于方法（访问文件之前、之后调用的方法 preVissitDirectory\postVissitDirectory等）
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println(file);
				return FileVisitResult.CONTINUE;
			}
			
		} );
	}
}
