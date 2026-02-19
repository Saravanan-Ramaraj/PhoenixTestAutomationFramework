package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {

	private ExcelReaderUtil2() {

	}

	public static <T> Iterator<T> loadTestData(String xlsxFile, String sheetname, Class<T> clazz) {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet mySheet = myWorkbook.getSheet(sheetname);

		List<T> dataList = Poiji.fromExcel(mySheet, clazz);

		return dataList.iterator();

	}

}
