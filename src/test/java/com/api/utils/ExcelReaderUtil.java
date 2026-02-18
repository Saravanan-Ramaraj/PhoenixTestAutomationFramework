package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkbook = new XSSFWorkbook(is);
		XSSFSheet mySheet = myWorkbook.getSheet("LoginTestData");

		XSSFRow myRow;
		XSSFCell myCell;

		int lastRowIndex = mySheet.getLastRowNum();
		System.out.println("Last row ind :" + lastRowIndex);

		XSSFRow rowHeader = mySheet.getRow(0);
		int lastIndexOfCol = rowHeader.getLastCellNum() - 1;
		System.out.println("Last col ind :" + lastIndexOfCol);

		for (int rowInd = 0; rowInd <= lastRowIndex; rowInd++) {
			for (int colInd = 0; colInd <= lastIndexOfCol; colInd++) {
				myRow = mySheet.getRow(rowInd);
				myCell = myRow.getCell(colInd);
				System.out.print(myCell + " ");
			}
			System.out.println(" ");
		}

	}

}
