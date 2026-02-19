package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.Usercredentials;

public class ExcelReaderUtil2 {

	private ExcelReaderUtil2() {

	}

	public static Iterator<Usercredentials> loadTestData() {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet mySheet = myWorkbook.getSheet("LoginTestData");
		XSSFRow myRow;
		XSSFCell myCell;

		// Read the excel file ----> Store it in the ArrayList

		// I want to know the indexes for the username and password in our sheet

		XSSFRow headerRow = mySheet.getRow(0);
		
		//Assuming that the username and passwords are not present. So indexes are -1
		int userNameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRow) {
			if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				userNameIndex = cell.getColumnIndex();
			}
			if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex = cell.getColumnIndex();
			}
		}

		System.out.println(userNameIndex + " " + passwordIndex);

		int lastRowindex = mySheet.getLastRowNum();
		XSSFRow rowData;
		Usercredentials usercredentials;
		ArrayList<Usercredentials> userList = new ArrayList<Usercredentials>();

		for (int rowIndex = 1; rowIndex <= lastRowindex; rowIndex++) {
			rowData = mySheet.getRow(rowIndex);
			usercredentials = new Usercredentials(rowData.getCell(userNameIndex).toString(),
					rowData.getCell(passwordIndex).toString());
			userList.add(usercredentials);
		}

		return userList.iterator();

	}

}
