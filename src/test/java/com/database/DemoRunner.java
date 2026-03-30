package com.database;

import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
		DatabaseManagerOld.createConeection();
		long startTime = System.currentTimeMillis();

		for (int i = 1; i <= 1000; i++) {
			DatabaseManagerOld.createConeection();
			DatabaseManagerOld.createConeection();
			DatabaseManagerOld.createConeection();
			DatabaseManagerOld.createConeection();
		}

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

	}

}
