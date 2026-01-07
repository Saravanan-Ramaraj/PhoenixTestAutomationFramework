package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.Usercredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() {
		Usercredentials userCredentials = new Usercredentials("iamfd", "password");

		given()
			.baseUri("http://64.227.160.186:9000/v1")
		.and()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.and()
			.body(userCredentials)
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
		.when()
			.post("login")
		.then()
			.statusCode(200)
			.time(lessThan(1000L))
		.and()
			.body("message", equalTo("Success"))
			.log().body()
		.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
