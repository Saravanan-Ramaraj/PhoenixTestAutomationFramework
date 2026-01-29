package com.api.tests;

import static com.api.constant.Roles.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

public class MasterAPITest {

	@Test(description = "Verify if the master API response is giving correct response", groups = {"api", "regression", "smoke"})
	public void masterAPITest() {
		given()
			.spec(requestSpecWithAuth(FD))
		.when()
			.post("/master") //default contetn-type application/url-formencoded
		.then()
			.spec(responseSpec_OK())
			.body("data", notNullValue())
			.body("data", hasKey("mst_oem"))
			.body("data", hasKey("mst_model"))
			.body("$", hasKey("message"))
			.body("data.mst_oem.size()", equalTo(2)) //Check the size of the JSON Array with Matchers
			.body("data.mst_model.size()", greaterThan(0))
			.body("data.mst_oem.id", everyItem(notNullValue()))
			.body("data.mst_oem.name", everyItem(notNullValue()))
			.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	
	@Test(description = "Verify if the master API response is giving correct response for invalid token", groups = {"api", "negative", "regression", "smoke"})
	public void invalidTokenMasterAPITest() {
		given()
			.spec(requestSpec())
	.when()
		.post("/master") //default contetn-type application/url-formencoded
	.then()
		.spec(responseSpec_TEXT(401));
	}
	
}
