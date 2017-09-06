package org.ada.study.tools.io.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**  
 * Filename: ExcelUtil.java  <br>
 * Excel 工具类(POI)
 * @author xsoftlab.net
 * @param <T>
 *            需要存储的类 - 支持基本数据类型及String,Date,byte[](图片数据)
 *  
 */

public class ExcelUtil<T> {
    // 表格标题
    private String sheetTitle = "Sheet1";
    // 列标题及列宽
    private String[][] headers = {};
    // 数据集
    private Collection<T> datasets;
    // 日期输出格式
    private String dateFormat = "yyyy-MM-dd";
    // 输出流
    private OutputStream out = null;
    // 图片行行高
    public static int PICLINEHEIGHT = 60;
    public ExcelUtil() {
        super();
    }
    public ExcelUtil(Collection<T> datasets, OutputStream out) {
        super();
        this.datasets = datasets;
        this.out = out;
    }
    public ExcelUtil(String sheetTitle, Collection<T> datasets, OutputStream out) {
        this(datasets, out);
        this.sheetTitle = sheetTitle;
    }
    public ExcelUtil(String[][] headers, Collection<T> datasets, OutputStream out) {
        this(datasets, out);
        this.headers = headers;
    }
    public ExcelUtil(String sheetTitle, String[][] headers, Collection<T> datasets, OutputStream out) {
        this(sheetTitle, datasets, out);
        this.headers = headers;
    }
    public ExcelUtil(String sheetTitle, String[][] headers, Collection<T> datasets, String dateFormat, OutputStream out) {
        this(sheetTitle, headers, datasets, out);
        this.dateFormat = dateFormat;
    }
    /**
     * 利用JAVA的反射机制，将集合中的数据输出到指定IO流中
     * 
     * 如有图片,需将图片字段（byte）的顺序与表格中的图片列顺序对应
     * 
     * @throws Exception
     *             异常
     */
    public void ExportExcel_xsl() throws Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetTitle);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 标题样式
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        // 设置水平居中
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 标题字体
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("微软雅黑");
        titleFont.setColor(HSSFColor.BLACK.index);
        titleFont.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);
        // 正文样式
        HSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.cloneStyleFrom(titleStyle);
        // 正文字体
        HSSFFont bodyFont = workbook.createFont();
        bodyFont.setFontName("宋体");
        bodyFont.setColor(HSSFColor.BLACK.index);
        bodyFont.setFontHeightInPoints((short) 12);
        bodyStyle.setFont(bodyFont);
        int index = 0;
        HSSFRow row = null;
        if (headers.length > 0) {
            // 产生表格标题行
            row = sheet.createRow(index++);
            // 设置行高
            row.setHeightInPoints(30f);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i][0]);
                cell.setCellValue(text);
                cell.setCellStyle(titleStyle);
                // 设置列宽
                sheet.setColumnWidth(i, Integer.parseInt(headers[i][1]) * 256);
            }
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = datasets.iterator();
        while (it.hasNext()) {
            row = sheet.createRow(index);
            // 设置行高
            row.setHeightInPoints(25f);
            T t = (T) it.next();
            // 利用反射，得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(bodyStyle);
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(t);
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                    textValue = sdf.format(date);
                } else if (value instanceof byte[]) {
                    // 设置图片行行高
                    row.setHeightInPoints(PICLINEHEIGHT);
                    byte[] bsValue = (byte[]) value;
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) i, index, (short) i, index);
                    anchor.setAnchorType(2);
                    patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                } else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value.toString();
                }
                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null) {
                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        cell.setCellValue(textValue);
                    }
                }
            }
            index++;
        }
        workbook.write(out);
    }
    public void ExportExcel_xslx() throws Exception {
        // 声明一个工作薄
    	Workbook workbook = new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(sheetTitle);
        // 声明一个画图的顶级管理器
        Drawing patriarch = sheet.createDrawingPatriarch();
        // 标题样式
        CellStyle titleStyle = workbook.createCellStyle();
        // 设置水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 标题字体
        Font titleFont = workbook.createFont();
        titleFont.setFontName("微软雅黑");
        titleFont.setColor(Font.BOLDWEIGHT_BOLD);
        titleFont.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);
        // 正文样式
        CellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.cloneStyleFrom(titleStyle);
        // 正文字体
        Font bodyFont = workbook.createFont();
        bodyFont.setFontName("宋体");
        bodyFont.setColor(Font.COLOR_NORMAL);
        bodyFont.setFontHeightInPoints((short) 12);
        bodyStyle.setFont(bodyFont);
        int index = 0;
        Row row = null;
        if (headers.length > 0) {
            // 产生表格标题行
            row = sheet.createRow(index++);
            // 设置行高
            row.setHeightInPoints(30f);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                XSSFRichTextString text = new XSSFRichTextString(headers[i][0]);
                cell.setCellValue(text);
                cell.setCellStyle(titleStyle);
                // 设置列宽
                sheet.setColumnWidth(i, Integer.parseInt(headers[i][1]) * 256);
            }
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = datasets.iterator();
        while (it.hasNext()) {
            row = sheet.createRow(index);
            // 设置行高
            row.setHeightInPoints(25f);
            T t = (T) it.next();
            System.out.println(t.toString());
            // 利用反射，得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(bodyStyle);
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(t);
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                    textValue = sdf.format(date);
                } else if (value instanceof byte[]) {
                    // 设置图片行行高
                    row.setHeightInPoints(PICLINEHEIGHT);
                	byte[] bsValue = (byte[]) value;
                	XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 1023, 255, (short) i, index, (short) i, index);
               	 	anchor.setAnchorType(2);
                	int pictureIdx = workbook.addPicture(bsValue, Workbook.PICTURE_TYPE_JPEG);
                	Picture pict = patriarch.createPicture(anchor, pictureIdx);
                	pict.resize();
                } else {
                    // 其它数据类型都当作字符串简单处理
                	if(null!=value){
                		textValue = value.toString();
                	}else{
                		textValue = "";
                	}
                }
                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null) {
                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        cell.setCellValue(textValue);
                    }
                }
            }
            index++;
        }
        workbook.write(out);
    }
    public static void main(String[] args) throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("c:/1.png"));
        byte[] buf = new byte[bis.available()];
        bis.read(buf);
        String[][] headers = { { "序号", "8" }, { "姓名", "20" }, { "年龄", "10" }, { "日期", "15" }, { "头像", "12" } };
        List<Student> dataset = new ArrayList<Student>();
        dataset.add(new Student(1, "张三", 21, new Date(), buf));
        dataset.add(new Student(2, "李四", 22, new Date(), buf));
        dataset.add(new Student(3, "王五", 23, new Date(), buf));
        OutputStream xslx = new FileOutputStream(new File("c:/5.xlsx"));
        //OutputStream xsl = new FileOutputStream(new File("c:/4.xls"));
        new ExcelUtil<Student>("班级人员", headers, dataset, xslx).ExportExcel_xslx();
        //new ExcelUtil<Student>("班级人员", headers, dataset, xsl).ExportExcel_xsl();
        System.out.println("excel导出成功！");
    }
}
class Student {
    public long id;
    public String name;
    public int age;
    public Date date;
    public byte[] b;
    public Student() {
        super();
    }
    public Student(long id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Student(long id, String name, int age, Date date) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
    }
    public Student(long id, String name, int age, Date date, byte[] b) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
        this.b = b;
    }
}
