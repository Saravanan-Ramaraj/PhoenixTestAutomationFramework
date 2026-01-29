package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Roles;
import com.api.constant.Service_Location;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {
	
	private CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creating createjob api request payload")
	public void setup() {
		// Creating the CreateJobPayload
				Customer customer = new Customer("Ram", "Kumar", "8562479620", "", "test@test.com", "");
				CustomerAddress customerAddress = new CustomerAddress("D098", "Urbanrise", "West st", "Chettinad", "Kelambakkam", "600040", "India", "Tamil Nadu");
				CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "13511800420678", "13511800420678", "13511800420678", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
				Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNINIG_SLOW.getCode(), "Battery Issue");
				List<Problems> problemList = new ArrayList<Problems>();
				problemList.add(problems);
				
				createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
				
	}
	

	@Test(description = "Verify if the create job API is able to create Inwarranty job", groups = {"api", "regression", "smoke"})
	public void createJobAPITest() {
		
		given()
			.spec(requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			.spec(responseSpec_OK())
			.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			.body("message", equalTo("Job created successfully. "))
			.body("data.mst_service_location_id", equalTo(1))
			.body("data.job_number", startsWith("JOB_"));
	}
}
