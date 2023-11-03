package com.user.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class DeleteUser {
	
	public static String randomEmail() {
		return "apiauto"+System.currentTimeMillis()+"@ggml.co.in";
	}
	
	@Test
	public void deleteUserTest() {
		
		UserPojo userpo = new UserPojo("cvbnn1",randomEmail(),"male","active");
		
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().log().all() 
				
				
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.contentType(ContentType.JSON)
		.body(userpo)
		.when().log().all()
		.post("/public/v2/users");
		System.out.println("==================");
		response.prettyPrint();
		System.out.println("==================");
		Integer userId = response.jsonPath().get("id");
		System.out.println(userId);
		
		System.out.println(">>>>>>>>>>>>>>>>>>");
		
		//delete USer 
		
		Response del = RestAssured.given().log().all() 
		
		
		.baseUri("https://gorest.co.in")
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.when().log().all()
		.delete("/public/v2/users/"+userId)
		.then()
		.assertThat()
		.statusCode(204)
		
		.and().extract().response();
		del.prettyPrint();
		
		//fetch it again to confirm
		
		Response getRes = RestAssured.given().log().all() 
				
				
				.baseUri("https://gorest.co.in")
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
				.when().log().all()
				.get("/public/v2/users/"+userId)
				.then()
				.assertThat()
				.statusCode(404)
				.and()
				.body("message", equalTo("Resource not found"))
				.and().extract().response();
				getRes.prettyPrint();
		
		
	}

}
