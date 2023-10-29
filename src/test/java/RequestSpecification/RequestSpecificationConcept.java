package RequestSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationConcept {
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec = new RequestSpecBuilder()
		.setBaseUri("https://gorest.co.in")
		.setContentType(ContentType.JSON)
		.addHeader("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.build();
		
		return requestSpec;
		
	}
	@Test
	public void getUserWith_UserSpec() {
	RestAssured.given()
	.spec(user_req_spec())
	.get("/public/v2/users")
	.then()
	.assertThat()
	.statusCode(200);
	
		
	}
	@Test
	public void getUserWith_UserSpec_QueryParams() {
		
		RestAssured.given().log().all()
		.spec(user_req_spec())
		.queryParam("name", "naveen")
		.queryParam("status", "active")
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}

}
