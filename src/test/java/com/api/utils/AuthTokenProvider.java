package com.api.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import static com.api.constant.Roles.*;

import com.api.constant.Roles;
import com.api.pojo.Usercredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	public static String getToken(Roles role)   {
		// I want to make the request for the login api and we want to extract the token
//		and print it on the console!!

		Usercredentials userCredentials = null;
		if (role == FD) {
			userCredentials = new Usercredentials("iamfd", "password");
		} else if (role == SUP) {
			userCredentials = new Usercredentials("iamsup", "password");
		} else if (role == ENG) {
			userCredentials = new Usercredentials("iameng", "password");
		} else if (role == QC) {
			userCredentials = new Usercredentials("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(userCredentials).when().post("login").then().log().ifValidationFails().statusCode(200)
				.body("message", equalTo("Success")).extract().body().jsonPath().getString("data.token");
		System.out.println("---------------------------------------------");
		System.out.println(token);
		return token;

	}

}
