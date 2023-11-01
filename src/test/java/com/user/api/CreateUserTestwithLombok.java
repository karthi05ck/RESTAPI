package com.user.api;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateUserTestwithLombok {
	
	public static String randomEmail() {
		return "apiauto"+System.currentTimeMillis()+"@ggml.co.in";
	}
	@Test
	public void createUserTest() {
	//Post user
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
	
	//Get user
	Response getResponse = RestAssured.given().log().all()
	.baseUri("https://gorest.co.in")
	.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
	.when().log().all()
	.get("/public/v2/users/"+userId);
	
	//de-serialize
	ObjectMapper mapper = new ObjectMapper();
	
	
	try {
		
	UserPojo userResponse =	mapper.readValue(getResponse.getBody().asString(), UserPojo.class);
	

	
	System.out.println(userResponse.getId() +":"+ userResponse.getEmail() + ":" + userResponse.getName());
	
	
	
	
		
		
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonProcessingException e) {
	//	// TODO Auto-generated catch block
	//	e.printStackTrace();
	}
	
	}

}
