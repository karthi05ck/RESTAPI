package xml;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetCircuitXMLAPITest {

	
	@Test
	public void xmlTest() {
		
		RestAssured.baseURI="https://ergast.com";
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().log().all()
		
		.when()
		.get("/api/f1/2017/circuits.xml")
		.then()
		.extract().response();
		System.out.println("the direct response is"  + response);
		System.out.println("==============");
		String responseBody = response.body().asString();
		System.out.println(responseBody);
		
		
		//create object of xmlpath:
		XmlPath xmlpath = new XmlPath(responseBody);
		List<String> circuitNames = xmlpath.getList("MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("Size of list is:" + circuitNames.size());
		for(String e : circuitNames) {
			System.out.println(e);
		}
		
		System.out.println("--------------------------------");
		
		//fetch locality where circuitId = americas 
		
		String locality = xmlpath.get("**.findAll {it.@circuitId =='americas'}.Location.Locality").toString();
		System.out.println(locality);
		
		System.out.println("--------------------------------");
		
		//fetch lat and long values of bahrain
		String latValue = xmlpath.get("**.findAll{it.@circuitId=='bahrain'}.Location.@lat");
		String longValue = xmlpath.get("**.findAll{it.@circuitId=='bahrain'}.Location.@long");
		System.out.println(latValue);
		System.out.println(longValue);
		
		System.out.println("--------------------------------");
		
		//fetch all the urls
		List<String> urls = xmlpath.getList("MRData.CircuitTable.Circuit.@url");
		System.out.println("Size of URL is:" + urls.size());
		for(String e : urls)
			System.out.println(e);
		
		System.out.println("--------------------------------");
		// fetch baku url
		String bakUrl = xmlpath.get("**.findAll{it.@circuitId=='baku'}.@url");
		System.out.println(bakUrl);
		
		//
		List<String> data = xmlpath.getList("**.findAll{it.@circuitId=='americas' || it.@circuitId == 'baku'}.Location.Locality");
		System.out.println(data.size());
		for(String e : data)
			System.out.println(e);
		
		List<String> longVValues = xmlpath.get("**.findAll{it.name()=='Location'}.@long");
		System.out.println(longVValues.size());
		for(String e : longVValues)
			System.out.println(e);
	}
		
	
	
}
