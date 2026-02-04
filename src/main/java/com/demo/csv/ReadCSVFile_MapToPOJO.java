package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPOJO {

	public static void main(String[] args) throws IOException, CsvException {
//		code to read the CSV file in java!! [Important interview question]

		InputStream is =  Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/logincreds.csv");
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		
		//Write the code to Map the CSV to POJO
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				 .withType(UserBean.class)
				 .withIgnoreEmptyLine(true)
				 .build();
		
		
		List<UserBean> userList =  csvToBean.parse();
		System.out.println(userList);
		System.out.println(userList.get(1).getUsername());
	}

}
