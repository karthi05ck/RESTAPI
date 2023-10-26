package com.rest.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetAPIRequestWithoutBDD {
	RequestSpecification request;
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI="https://gorest.co.in";
		RestAssured.useRelaxedHTTPSValidation();
		 request = RestAssured.given();
		request.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79");
		
	}
	@Test
	public void getApiTest() {
		
		
		Response response = request.get("/public/v2/users");
		
		int status = response.getStatusCode();
		System.out.println("The status we got is" + status);
		Assert.assertEquals(status, 200);
		
		String statusMessage = response.statusLine();
		System.out.println(statusMessage);
		
		response.prettyPrint();
		
		String contentType = response.header("Content-Type");
		System.out.println(contentType);
		
		System.out.println("===================");
		List<Header> headerList = response.headers().asList();
		System.out.println("The size is:" + headerList.size());
		
		for(Header header : headerList) {
			System.out.println(header.getName() + ":" + header.getValue() );
		}
		
	}
	
	@Test
	public void getApiTestWithQueryParams() {
		
		request.param("name", "naveen");
		request.param("status", "active");
		Response response = request.get("/public/v2/users");
		
		int status = response.getStatusCode();
		System.out.println("The status we got is" + status);
		Assert.assertEquals(status, 200);
		
		String statusMessage = response.statusLine();
		System.out.println(statusMessage);
		
		response.prettyPrint();
	}
	
	
	@Test
	public void getApiTestWithQueryParamsAsMap() {
//		RestAssured.baseURI="https://gorest.co.in";
//		RestAssured.useRelaxedHTTPSValidation();
//		RequestSpecification request = RestAssured.given();
//		request.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79");
		Map<String,String> queryParamMap = new HashMap<String,String>();
		queryParamMap.put("name", "naveen");
		queryParamMap.put("status", "inactive");
		request.params(queryParamMap);
		//request.param("name", "naveen");
		//request.param("status", "active");
		Response response = request.get("/public/v2/users");
		
		int status = response.getStatusCode();
		System.out.println("The status we got is" + status);
		Assert.assertEquals(status, 200);
		
		String statusMessage = response.statusLine();
		System.out.println(statusMessage);
		
		response.prettyPrint();
	}

}
