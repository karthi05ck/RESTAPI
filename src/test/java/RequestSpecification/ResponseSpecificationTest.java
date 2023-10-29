package RequestSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class ResponseSpecificationTest {
	
	public static ResponseSpecification user_response_spec_200() {
		ResponseSpecification response = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("server", "cloudflare")
		.build();
		
		return response;
		
		
	}
	public static ResponseSpecification user_response_spec_200_WithBody() {
		ResponseSpecification response = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("server", "cloudflare")
		.expectBody("id", hasSize(10))
		.expectBody("$.size()", equalTo(10))
		.build();
		
		return response;
		
		
	}
	public static ResponseSpecification user_response_spec_401() {
		ResponseSpecification response = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(401)
		.expectHeader("server", "cloudflare")
		.build();
		
		return response;
		
		
	}
	
	
	
	@Test
	public void getUser_with_200_spec_Test() {
		RestAssured.given().log().all()
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(user_response_spec_200_WithBody());
	}

	@Test
	public void getUser_with_401_spec_Test() {
		RestAssured.given().log().all()
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer 885c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(user_response_spec_401());
	}
}
