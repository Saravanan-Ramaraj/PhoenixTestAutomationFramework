package com.database;

import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
		DatabaseManager.createConeection();
		long startTime = System.currentTimeMillis();

		for (int i = 1; i <= 1000; i++) {
			DatabaseManager.createConeection();
			DatabaseManager.createConeection();
			DatabaseManager.createConeection();
			DatabaseManager.createConeection();
		}

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

	}

}
