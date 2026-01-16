package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.constant.Roles.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.headers("Authorization", getToken(FD))
			.and()
			.contentType("")
		.when()
			.post("/master") //default contetn-type application/url-formencoded
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1000L))
			.body("message", equalTo("Success"))
			.body("data", notNullValue())
			.body("data", hasKey("mst_oem"))
			.body("data", hasKey("mst_model"))
			.body("$", hasKey("message"))
			.body("data.mst_oem.size()", equalTo(2)) //Check the size of the JSON Array with Matchers
			.body("data.mst_model.size()", greaterThan(0))
			.body("data.mst_oem.id", everyItem(notNullValue()))
			.body("data.mst_oem.name", everyItem(notNullValue()))
			.body(matchesJsonSchemaInClasspath("response-schema/masterAPIResponseSchema.json"));
	}
	
	
	@Test
	public void invalidTokenMasterAPITest() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.headers("Authorization", "")
		.and()
		.contentType("")
		.log().all()
	.when()
		.post("/master") //default contetn-type application/url-formencoded
	.then()
		.log().all()
		.statusCode(401);
	}
	
}
