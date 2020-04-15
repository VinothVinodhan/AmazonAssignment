package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	Logger log = Logger.getLogger(ExcelRead.class);
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	String xlFilePath;

	public ExcelRead(String xlFilePath) {
		this.xlFilePath = xlFilePath;
		try {
			fis = new FileInputStream(xlFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e);
			BaseSuite.logException(e);
		}
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e);
			BaseSuite.logException(e);
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e);
			BaseSuite.logException(e);
		}
	}

	public int getRowCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() + 1;
		return rowCount;
	}

	public int getColumnCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		return colCount;
	}

	public Object getCellData(String sheetName, int j, int i) {
		sheet = workbook.getSheet(sheetName);
		int c = sheet.getRow(i).getCell(j).getCellType();
		System.out.println(c);
		if (c == 0) {
			return NumberToTextConverter.toText(sheet.getRow(i).getCell(j).getNumericCellValue());
		} else {
			return sheet.getRow(i).getCell(j).getStringCellValue();
		}

	}
}
