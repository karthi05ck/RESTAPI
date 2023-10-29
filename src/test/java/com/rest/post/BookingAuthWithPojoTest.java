package com.rest.post;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class BookingAuthWithPojoTest {
	
	//pojo - plain old java object
	//can not extend any other class
	// oop - encapsulation
	//private class vars - json body
	// public getter and setter
	
	// Serialization -> java object to other format: file, media, json/xml/text/pdf
	//pojo to json -> serialization
	//json to pojo -> deserialization
	// add jackson bind dependency for the serialization
	
	@Test
	public void getBookingAuthTest_WithJSONString() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Credentials cred = new Credentials("admin","password123");
		String token = given().log().all()
		.contentType(ContentType.JSON)
		.body(cred)
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.extract().path("token");
		System.out.println(token);
		Assert.assertNotNull(token);
		
	}

}
