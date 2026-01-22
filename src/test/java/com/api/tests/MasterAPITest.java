package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.constant.Roles.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
			.post("/master") //default contetn-type application/url-formencoded
		.then()
			.spec(SpecUtil.responseSpec_OK())
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
	
	
	@Test
	public void invalidTokenMasterAPITest() {
		given()
			.spec(SpecUtil.requestSpec())
	.when()
		.post("/master") //default contetn-type application/url-formencoded
	.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
	
}
