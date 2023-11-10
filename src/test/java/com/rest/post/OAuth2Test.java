package com.rest.post;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAuth2Test {
static String accessToken;
	@BeforeMethod
	
	public void getAccessToken() {
		//1.With post - get the access token
		RestAssured.baseURI="https://test.api.amadeus.com";
	RestAssured.useRelaxedHTTPSValidation();
		 accessToken = RestAssured.given()
		.header("Content-Type","application/x-www-form-urlencoded")
		.formParam("grant_type", "client_credentials")
		.formParam("client_id", "TAnRnsU5lASXZ8mPGdwRQZMoQzhu6Gwv")
		.formParam("client_secret", "VjjgfcJilNAzcSJw")
		.when()
		.post("/v1/security/oauth2/token")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().path("access_token");
		System.out.println(accessToken);
		
		
		
	}
	@Test
	public void getFlightInfoTest() {
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().log().all()
		.header("Authorization", "Bearer "+accessToken)
		.queryParam("origin", "PAR")
		.queryParam("maxPrice", 200)
		.when().log().all()
		.get("/v1/shopping/flight-destinations")
		.then()
		.assertThat()
		.statusCode(200) 
		.extract().response();
		System.out.println("Response pretty//////////////");
		response.prettyPrint();
		
		System.out.println("The new line==========>");
		JsonPath js = response.jsonPath();
		System.out.println(js.prettyPrint());
	}
}
