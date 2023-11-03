package com.user.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class UpdateUser {

	public static String randomEmail() {
		return "apiauto"+System.currentTimeMillis()+"@ggml.co.in";
	}
	
	@Test
	public void updateUserTest() {
		
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
		//update the exisiting user
		userpo.setName("NewNAME");
		userpo.setGender("female");
		userpo.setStatus("INACTIVE");
		
		// Put call
		
		RestAssured.useRelaxedHTTPSValidation();
		 RestAssured.given().log().all() 
				
				
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.contentType(ContentType.JSON)
		.body(userpo)
		.when().log().all()
		.put("/public/v2/users/"+userId)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(userId))
		.and()
		.body("name", equalTo(userpo.getName()))
		.and()
		.body("gender", equalTo(userpo.getGender()));
		
		
		
		
	}
}
