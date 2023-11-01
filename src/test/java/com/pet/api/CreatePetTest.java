package com.pet.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetTestPojo.Category;
import com.pet.api.PetTestPojo.Tags;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePetTest {
	
	@Test
	public void postPetTest() {
		
		
		RestAssured.baseURI="https://petstore.swagger.io";
		RestAssured.useRelaxedHTTPSValidation();
		Category category = new Category(1, "Dog12");
	//	List<String> photoUrls = Arrays.asList("photo1.jpg", "photo2.jpg");
		List<String> photoUrls = new ArrayList<String>();
		photoUrls.add("photo1.jpgggg");
		photoUrls.add("photo2.jpggg");
		
		Tags tag1 = new Tags(10, "tag1");
		Tags tag2 = new Tags(20, "tag2");
		
		List<Tags> tags = Arrays.asList(tag1, tag2);
		PetTestPojo pet = new PetTestPojo(122345, category, "karCcCreate", photoUrls, tags, "1Active12");
		
		Response response = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.body(pet)
		.when().log().all()
		.post("/v2/pet");
		System.out.println(response.statusCode());
		response.prettyPrint();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			PetTestPojo petRes =mapper.readValue(response.getBody().asString(), PetTestPojo.class);
			System.out.println("=========");
			System.out.println(petRes);
			System.out.println("=========");
			System.out.println(petRes.getStatus());
			Assert.assertEquals(petRes.getStatus(), pet.getStatus());
			System.out.println(petRes.getId());
			Assert.assertEquals(petRes.getId(), pet.getId());
			System.out.println(petRes.getName());
			Assert.assertEquals(petRes.getName(), pet.getName());
			
			System.out.println(petRes.getPhotoUrls());
			Assert.assertEquals(petRes.getPhotoUrls(), pet.getPhotoUrls());
			
			
			System.out.println(petRes.getCategory().getId());
			Assert.assertEquals(petRes.getCategory().getId(), pet.getCategory().getId());
			
			System.out.println(petRes.getCategory().getName());
			Assert.assertEquals(petRes.getCategory().getName(), pet.getCategory().getName());
			
			System.out.println(petRes.getTags());
			
			System.out.println(petRes.getTags().get(0).getId());
			Assert.assertEquals(petRes.getTags().get(0).getId(), pet.getTags().get(0).getId());
			
			System.out.println(petRes.getTags().get(0).getName());
			Assert.assertEquals(petRes.getTags().get(0).getName(), pet.getTags().get(0).getName());
			
			
			
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}
	
	
	

}
