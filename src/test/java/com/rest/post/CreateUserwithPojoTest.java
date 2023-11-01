package com.rest.post;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

public class CreateUserwithPojoTest {
	
	public static String getRandomEmailId() {
		return  "automationapiTest"+System.currentTimeMillis()+"@maill.com";
		//return "automationapiTest"+UUID.randomUUID()+"@maill.com";
		
	}
	@Test
	
	public void addUserTest() {
		//1. post and get userid
		User user = new User("dhivs55",getRandomEmailId(),"female","active");
		RestAssured.useRelaxedHTTPSValidation();
		int userId = RestAssured.given().log().all()
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.contentType(ContentType.JSON)
		.body(user)
		.when().log().all()
		.post("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.and()
		.body("name", equalTo(user.getName()))
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
		.body("id", equalTo(userId))
		.and()
		.body("name", equalTo(user.getName()))
		.and()
		.body("email", equalTo(user.getEmail()));
		
		
		
		
	}

}
