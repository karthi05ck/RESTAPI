package com.product.api;

import org.testng.annotations.Test;

import com.fake.api.ProductLombok;
import com.fake.api.ProductLombok.Rating;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProductAPITest {
	@Test
	public void postProductTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		RestAssured.useRelaxedHTTPSValidation();
		Rating rate = new Rating(25.55, 20);
		ProductLombok pL= new ProductLombok("A new title", 10.50, "A description provided", "Book category", "httpe:www.img1.abc", rate);
		
		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
		.body(pL)
		.when().log().all()
		.post("/products");
		response.prettyPrint();
		System.out.println("Value from path in direct is:" + response.path("id"));
		
		JsonPath js = response.jsonPath();
		System.out.println("Value from path in direct is:" + js.getInt("id"));
		
	}
	@Test
	public void getproductTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().log().all()
		
		.when().log().all()
		.get("/products");
		
	ObjectMapper mapper = 	new ObjectMapper();
	try {
	ProductLombok[] product =	mapper.readValue(response.getBody().asString(), ProductLombok[].class);
		
	
	for (ProductLombok p : product) {
		System.out.println("ID: " + p.getId());
		System.out.println("description: " + p.getDescription());
		System.out.println("Rating rate: " + p.getRating().getRate());
		System.out.println("===========================");
	}
		
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}

}
