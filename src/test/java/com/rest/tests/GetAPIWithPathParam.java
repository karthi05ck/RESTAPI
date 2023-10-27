package com.rest.tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetAPIWithPathParam {
	//query vs path
	//<k,v>  vs  <any key, value>
	@Test
	public void getAPIwithPathParamTest() {
		RestAssured.baseURI="http://ergast.com";
		
		given().log().all()
		.pathParam("year", "2017")
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.season", equalTo("2017"))
		.and()
		.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
		
	}
	@DataProvider
	public Object[][] dataProviderforCircuit() {
		return new Object[][]{
			{"2016",21},
			{"2017",20},
			{"2023",22},
			
		};
		
	}
	
	@Test(dataProvider = "dataProviderforCircuit")
	public void getAPIwithPathParamWith_DataProviderTest(String circuitYear, int circuitId) {
		RestAssured.baseURI="http://ergast.com";
		
		given().log().all()
		.pathParam("year", "2017")
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.season", equalTo(circuitYear))
		.and()
		.body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitId));
	}

}
