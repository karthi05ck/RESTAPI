package JSONPathValidationJayway;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathTest {
	
	@Test
	public void getAPI_JsonJayway() {
		//using Json jay way dependency
		// store response from get()
		// convert to sting ->response.asString
		//JsonPath.read(response, "JQL expression"
	
	
	RestAssured.baseURI="http://ergast.com";
	
	Response response = given().log().all()
	
	.when().log().all()
	.get("/api/f1/2019/circuits.json");
	
	String jsonResponse = response.asString();
	System.out.println(jsonResponse);
	
	int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
	System.out.println(totalCircuits);
	
	List<String> countryList = JsonPath.read(jsonResponse,"$..Circuits..country" );
	System.out.println(countryList.size());
	System.out.println(countryList);
	
	
	
	
	
	}

}
