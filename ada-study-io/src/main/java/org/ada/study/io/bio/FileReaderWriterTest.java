package org.ada.study.io.bio;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 写出、写入的概念：
 * 		相对于系统内存，output将系统内存数据写出到磁盘；input将磁盘数据写入到系统内存
 * 
 * 以文本格式写出数据：PrintWriter
 * 以文本格式写入数据：Scanner Java5的新特征，主要功能是简化文本扫描，本案例使用到换行读取
 * 以二进制格式写出数据，DataOutputStream
 * 
 * 注意：
 * 		系统换行符，根据系统不同，可能不尽相同，可以通过
 * 		System.getProperty("line.separator") 获取符号。
 * 		window '\r\n' ; unix '\n'
 * 		系统 文件分隔符，建议使用java.io.File.separator获取，否则移植性弱.
 *  	如：String path = "c:\\Windows\xxx.txt"; 
 *  	可动态在程序中拼写 
 *  	  String path = "c:"+java.io.File.separator+"Windows"+java.io.File.separator+"xxx.txt"; 
 * 
 * 
 * FileName FileReaderWriterTest
 * @author: CZD
 * @Createtime: 2017年6月17日
 */
public class FileReaderWriterTest {
	public static void main(String[] args) {
		//写文件
		FileReaderWriterTest.writeStart();
		//读文件
		FileReaderWriterTest.readStart();
	}

	/**
	 * 读取文件
	 */
	public static void readStart(){
		try(Scanner in = new Scanner(new FileInputStream("file.data"))){
			Employee[] es = readData(in);
			for(Employee e:es)
				System.out.println(e.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 读取集合内容，封装为集合实体
	 * @param in
	 * @return
	 */
	public static Employee[] readData(Scanner in){
		String line = in.nextLine();
		int size = Integer.valueOf(line);
		Employee[] es = new Employee[size];
		Employee e = null;
		for(int i = 0;i<size ;i++){
			e = readEmployee(in);
			es[i] = e;
		}
		return es;
	}
	
	/**
	 * 读取单行内容，封装为实体
	 * @param in
	 * @return
	 */
	public static Employee readEmployee(Scanner in){
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		Employee e = new Employee(tokens[0], Double.valueOf(tokens[1]), 0, 0, 0);
		return e;
	}
	/**
	 * 写入文件
	 */
	public static void writeStart() {
		// java.io 相对路径解释为以用户工作目录开始，该属性可以获取路径信息
		String path = System.getProperty("user.dir");
		System.out.println(path);
		PrintWriter out = null;
		try {
			// 快捷链接FileWriter
			out = new PrintWriter("file.data","UTF-8");
			Employee e = new Employee("name", Double.valueOf("2.3"), 2017, 6,
					17);
			writeData(new Employee[] { e }, out);
			out.flush();// 刷新到磁盘
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * 集合实体写入的业务封装
	 * @param employees
	 * @param out
	 */
	public static void writeData(Employee[] employees, PrintWriter out) {
		out.println(employees.length);
		for (Employee employee : employees)
			writeEmployee(employee, out);
	}

	/**
	 * 实体写入的业务封装
	 * @param e
	 * @param out
	 */
	public static void writeEmployee(Employee e, PrintWriter out) {
		GregorianCalendar calendar = new GregorianCalendar();
		e.setTime(calendar.getTime());
		out.println(e.getName() + "|" + e.getSalary());
	}

	/**
	 * 员工实体类
	 */
	static class Employee {
		public Employee(String name, Double salary, Integer year,
				Integer month, Integer day) {
			this.name = name;
			this.salary = salary;
			this.year = year;
			this.month = month;
			this.day = day;
		}

		private String name;
		private Double salary;
		private Integer year;
		private Integer month;
		private Integer day;
		private Date time;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getSalary() {
			return salary;
		}

		public void setSalary(Double salary) {
			this.salary = salary;
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		public Integer getMonth() {
			return month;
		}

		public void setMonth(Integer month) {
			this.month = month;
		}

		public Integer getDay() {
			return day;
		}

		public void setDay(Integer day) {
			this.day = day;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		@Override
		public String toString() {
			return "Employee [name=" + name + ", salary=" + salary + ", year="
					+ year + ", month=" + month + ", day=" + day + ", time="
					+ time + "]";
		}
	}
}
