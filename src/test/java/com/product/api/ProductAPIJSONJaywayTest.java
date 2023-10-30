package com.product.api;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPIJSONJaywayTest {

	@Test
	public void getProduct_BDD_Json_Jayway_Test() {
		RestAssured.baseURI="https://fakestoreapi.com";
		RestAssured.useRelaxedHTTPSValidation();
		
		Response response = RestAssured.given().
		when()
		.get("/products");
		
		String jsresponse = response.asString();
		System.out.println(jsresponse);
		
	List<String> titles = JsonPath.read(jsresponse, "$[*].title");
	System.out.println(titles.size());
	System.out.println(titles);
		
	List<String> titleLessthanThree = JsonPath.read(jsresponse, "$[?(@.rating.rate < 3)].title");
	System.out.println(titleLessthanThree);
		System.out.println("================================");
		
		//with two attributes
	List<Map<String, Object>> jewelList = 	JsonPath.read(jsresponse, "$[?(@.category=='jewelery')].[\"title\",\"price\"]");
	System.out.println(jewelList);
	
	for(Map<String, Object> product : jewelList) {
		
	String title =(String) product.get("title");
	Object price = (Object)product.get("price");
	
	System.out.println("title is : " + title);
	System.out.println("price is : " + price);
	
	}
	System.out.println("======== attri over========================");
	
	
	List<Map<String, Object>> jewelList2 = 	JsonPath.read(jsresponse, "$[?(@.category=='jewelery')].[\"title\",\"price\",\"id\"]");
	System.out.println(jewelList2);
	
	for(Map<String, Object> product : jewelList2) {
	Object title =(Object) product.get("title");
	Object price = (Object)product.get("price");
	Object id = (Object)product.get("id");
	
	System.out.println("title is : " + title);
	System.out.println("price is : " + price);
	System.out.println("id is : " + id);
	}
	System.out.println("========3 attri over========================");
	}
	
}
