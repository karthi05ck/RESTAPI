package com.rest.tests;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAPIRequestWITHBDD {

	//without header
	@Test
	public void getProductsTest() {
		
		useRelaxedHTTPSValidation();
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("Connection", "keep-alive")
												.and()
													.body("$.size()", equalTo(20))
														.and()
															.body("id", is(notNullValue()))
																.and()
																	.body("title", hasItem("Mens Cotton Jacket"));
																	
																	
															
	}
	//with header alone in BDD approach
	@Test
	public void getUserTest() {
		RestAssured.baseURI="https://gorest.co.in";
		useRelaxedHTTPSValidation();
		
		given().log().all()
			.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
			.when().log().all()
			.get("/public/v2/users")
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.contentType(ContentType.JSON)
			.and()
			.body("$.size()", equalTo(10));
	}
	
	//with query parameter alone in BDD approach
	@Test
	public void getProductAPIWithQueryParam() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		useRelaxedHTTPSValidation();
		
		given().log().all()
		.queryParam("limit", 5)
		.queryParam("name", "naveen")
		.when().log().all()
		.get("/products")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON);
		
		
	}
	
	
	//storing the response and  Extracting the value from body individually
	@Test
	public void getProductAPIWithExtractBody() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		useRelaxedHTTPSValidation();
		
		Response response = given().log().all()
		.queryParam("limit", 5)
		.queryParam("name", "naveen")
		.when().log().all()
		.get("/products");
		
		//response.prettyPrint();
	JsonPath js = response.jsonPath();
	int id = js.getInt("[0].id");
	System.out.println("The id value is:" + id);
	
	String fourthidTitle = js.getString("[3].title");
	System.out.println("FourthIdTitle is :" + fourthidTitle);
	
	Float price = js.getFloat("[0].price");
	System.out.println("The price value of 0 index array is:" + price);
	
	int count = js.getInt("[0].rating.count");
	System.out.println("The count value  of 0 index array is:" + count);
	}
	
	
	//storing the response and  Extracting the value from body as List and iterating it
	@Test
	public void getProductAPIWithExtractBodyWithArray() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		useRelaxedHTTPSValidation();
		
		Response response = given().log().all()
		.queryParam("limit", 5)
		.queryParam("name", "naveen")
		.when().log().all()
		.get("/products");
		
		JsonPath js = response.jsonPath();
		List<Integer> idList = js.getList("id");
		List<String> titleList = js.getList("title");
		List<Float> rateList = js.getList("rating.rate");
		List<Integer> countList = js.getList("rating.count");
		
		for(int i=0; i<idList.size();i++) {
			int id = idList.get(i);
			String title = titleList.get(i);
			float rate = rateList.get(i);
			int count = countList.get(i);
			System.out.println("Id is  :" +id +" " + "Title is:" +title + " " + "Rate is :" +rate + " " + "Count is :" +count);
		}
		
	}
		
	//storing the response and extracting
		@Test
		public void getUserAPI_WithExtractFromResponse() {
			RestAssured.baseURI="https://gorest.co.in";
			useRelaxedHTTPSValidation();
			
			Response response = given().log().all()
				.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
				.when().log().all()
				.get("/public/v2/users/5500263");
			
			JsonPath js = response.jsonPath();
			System.out.println(js.getInt("id"));
			System.out.println(js.getString("email"));
			
		
	}
		//extracting directly using extract method
		@Test
		public void getUserAPI_WithExtractMethodDirectly_Way1() {
			RestAssured.baseURI="https://gorest.co.in";
			useRelaxedHTTPSValidation();
			
			int userId = given().log().all()
				.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
				.when().log().all()
				.get("/public/v2/users/5500277")
				.then()
				.extract().path("id");
				
			System.out.println(userId);
		}
			
			@Test
			public void getUserAPI_WithExtractMethodDirectly_Way2() {
				RestAssured.baseURI="https://gorest.co.in";
				useRelaxedHTTPSValidation();
				
				Response response = given().log().all()
					.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
					.when().log().all()
					.get("/public/v2/users/5500263")
					.then()
					.extract().response();
					
				//System.out.println(response.path("id"));
				//System.out.println(response.path("email"));
			
		
	}
	
	
	
	
	
}

