package cn.itcast.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

class TestPOI2Excel {

	@Test
	void testWritre03Excel() throws Exception {
		// 1.创建工作簿
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 2.创建工作表
		HSSFSheet sheet = workBook.createSheet("hello world");
		// 3.创建行
		HSSFRow row = sheet.createRow(3);
		// 4.创建单元格
		HSSFCell cell = row.createCell(3);
		// 5.单元格赋值
		cell.setCellValue("hello world");
		// 6.创建再内存中，应该输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("D:\\sa\\my.xls");
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
	}

	@Test
	void testRead03Excel() throws Exception {
		FileInputStream InputStream = new FileInputStream("d:\\sa\\my.xls");
		// 1.读取工作簿
		HSSFWorkbook workBook = new HSSFWorkbook(InputStream);
		// 2.读取第一个工作表
		HSSFSheet sheet = workBook.getSheetAt(0);
		// 3.读取行
		HSSFRow row = sheet.getRow(3);
		// 4.读取单元格
		HSSFCell cell = row.getCell(3);
		// 5.输出值
		System.out.println(cell.getStringCellValue());
		// 6.创建再内存中，应该输出到硬盘
		workBook.close();
		InputStream.close();
	}

	@Test
	void testWritre07Excel() throws Exception {
		// 1.创建工作簿
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 2.创建工作表
		XSSFSheet sheet = workBook.createSheet("hello world");
		// 3.创建行
		XSSFRow row = sheet.createRow(3);
		// 4.创建单元格
		XSSFCell cell = row.createCell(3);
		// 5.单元格赋值
		cell.setCellValue("hello world");
		// 6.创建再内存中，应该输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("D:\\sa\\my.xlsx");
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
	}

	@Test
	void testWritreStyle() throws Exception {
		// 1.创建工作簿
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 1.1创建单元格合并对象
		CellRangeAddress cellRangeAddress = new CellRangeAddress(3, 3, 3, 4);
		// 1.2创建单元格样式
		XSSFCellStyle style = workBook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		// 1.3创建字体
		XSSFFont Font = workBook.createFont();
		Font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 加粗字体
		Font.setFontHeightInPoints((short) 16); // 设置字体的的大小
		// 加载字体
		style.setFont(Font);
		// 单元格背景
		// 设置背景填充模式
		// style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置填充背景色
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		// 设置前景色
		style.setFillBackgroundColor(HSSFColor.RED.index);
		// 2.创建工作表
		XSSFSheet sheet = workBook.createSheet("hello world");
		// 2.1加载单元格合并对象
		sheet.addMergedRegion(cellRangeAddress);
		// 3.创建行
		XSSFRow row = sheet.createRow(3);
		// 4.创建单元格
		XSSFCell cell = row.createCell(3);
		// 加载单元格样式
		cell.setCellStyle(style);
		// 5.单元格赋值
		cell.setCellValue("hello world");
		// 6.创建再内存中，应该输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("D:\\sa\\my.xlsx");
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
	}

	@Test
	void testRead07Excel() throws Exception {
		FileInputStream InputStream = new FileInputStream("d:\\sa\\my.xlsx");
		// 1.读取工作簿
		XSSFWorkbook workBook = new XSSFWorkbook(InputStream);
		// 2.读取第一个工作表
		XSSFSheet sheet = workBook.getSheetAt(0);
		// 3.读取行
		XSSFRow row = sheet.getRow(3);
		// 4.读取单元格
		XSSFCell cell = row.getCell(3);
		// 5.输出值
		System.out.println(cell.getStringCellValue());
		// 6.创建再内存中，应该输出到硬盘
		workBook.close();
		InputStream.close();
	}

	@Test
	void testRead03And07Excel() throws Exception {
		String fileName = "d:\\sa\\my.xlsx";
		if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) { 
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
			FileInputStream InputStream = new FileInputStream(fileName);

			// 1.读取工作簿
			Workbook workBook = is03Excel ? new HSSFWorkbook(InputStream) : new XSSFWorkbook(InputStream);
			// 2.读取第一个工作表
			Sheet sheet = workBook.getSheetAt(0);
			// 3.读取行
			Row row = sheet.getRow(3);
			// 4.读取单元格
			Cell cell = row.getCell(3);
			// 5.输出值
			System.out.println(cell.getStringCellValue());
			// 6.创建再内存中，应该输出到硬盘
			workBook.close();
			InputStream.close();
		}
	}

}
