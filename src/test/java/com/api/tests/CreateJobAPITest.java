package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {

	
	

	@Test
	public void createJobAPITest() {
		// Creating the CreateJobPayload
		Customer customer = new Customer("Ram", "Kumar", "8562479620", "", "test@test.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D098", "Urbanrise", "West st", "Chettinad", "Kelambakkam", "600040", "India", "Tamil Nadu");
		CustomerProduct customerProduct = new CustomerProduct("2025-10-21T18:30:00.000Z", "12410700420675", "12410700420675", "12410700420675", "025-10-21T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			.spec(SpecUtil.responseSpec_OK());
	}
}
