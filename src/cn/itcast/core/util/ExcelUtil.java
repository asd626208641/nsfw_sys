package cn.itcast.core.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.itcast.nsfw.user.entity.User;

public class ExcelUtil {
	public static void exportExcel(List<User> userList, ServletOutputStream outputStream) {
		try {
			// 1、创建工作簿
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);

			// 1.2、头标题样式
			XSSFCellStyle style1 = createCellStyle(workbook, (short) 16);

			// 1.3、列标题样式
			XSSFCellStyle style2 = createCellStyle(workbook, (short) 10);

			//
			// 2、创建工作表
			XSSFSheet sheet = workbook.createSheet("用户列表");
			// 2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			// 设置单元格默认的列宽
			sheet.setDefaultColumnWidth(25);
			// 3、创建行
			// 3.1 创建头标题行，并且设置头标题
			XSSFRow row1 = sheet.createRow(0);
			XSSFCell cell1 = row1.createCell(0);
			// 加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");
			// 3.2 创建头标题行，并且设置头标题
			XSSFRow row2 = sheet.createRow(1);
			String[] titles = { "用戶名", "账号", "所属部门", "性别", "电子邮箱" };
			for (int i = 0; i < titles.length; i++) {
				XSSFCell cell2 = row2.createCell(i);
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			// 加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");

			// 4、操作单元格；将用户列表写入excel
			if (userList != null) {
				for (int j = 0; j < userList.size(); j++) {
					XSSFRow row = sheet.createRow(j + 2);
					XSSFCell cell11 = row.createCell(0);
					cell11.setCellValue(userList.get(j).getName());
					XSSFCell cell12 = row.createCell(1);
					cell12.setCellValue(userList.get(j).getAccount());
					XSSFCell cell13 = row.createCell(2);
					cell13.setCellValue(userList.get(j).getDept());
					XSSFCell cell14 = row.createCell(3);
					cell14.setCellValue(userList.get(j).isGender() ? "男" : "女");
					XSSFCell cell15 = row.createCell(4);
					cell15.setCellValue(userList.get(j).getEmail());
				}
			}
			// 5、输出
			workbook.write(outputStream); // 将workbook写在输出流中进行输出
			workbook.close();
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontSize) { // 这是xlsx格式，如果要xls格式，要改代码
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平居中
//		style.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);// xls格式的垂直居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// xlsx格式的垂直居中
		// 创建字体
		XSSFFont Font = workbook.createFont();
		Font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		Font.setFontHeightInPoints(fontSize);
		// 加载字体
		style.setFont(Font);
		return style;
	}
}
