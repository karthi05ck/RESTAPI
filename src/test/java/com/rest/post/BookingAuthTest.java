package com.rest.post;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import  io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BookingAuthTest {
	
	@Test
	public void getBookingAuthTest_WithJSONString() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String token = given().log().all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "\"username\":\"admin\",\r\n"
				+ "\"password\":\"password123\"\r\n"
				+ "}")
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.extract().path("token");
		System.out.println(token);
		Assert.assertNotNull(token);
		
	}
	@Test
	public void getBookingAuthTest_WithJSON_File() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		 String token = given().log().all()
		.contentType(ContentType.JSON)
		.body(new File(".\\src\\test\\resources\\data\\basicauth.json"))
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.extract().path("token");
		System.out.println(token);
		Assert.assertNotNull(token);
	
	}
	
	@Test
	public void addUserTest() {
		//1. post and get userid
		int userId = RestAssured.given().log().all()
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.contentType(ContentType.JSON)
		.body(new File(".\\src\\test\\resources\\data\\adduser.json"))
		.when().log().all()
		.post("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.and()
		.body("name", equalTo("kkkdddkard"))
		.extract().path("id");
		System.out.println("The userid is :"+userId);
		
		//2. append the userid for the GET call
		RestAssured.given().log().all()
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.when().log().all()
		.get("/public/v2/users/"+ userId)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(userId));
		
		
		
		
	}
	
	
	
	

}
