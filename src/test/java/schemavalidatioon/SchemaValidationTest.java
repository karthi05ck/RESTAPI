package schemavalidatioon;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


public class SchemaValidationTest {
	
	@Test
	public void addUserSchemaTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79")
		.body(new File(".\\src\\test\\resources\\data\\adduser.json"))
		.when()
		.post("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.and()
		//.body(matchesJsonSchemaInClasspath("CreateUserSchema.json"));
		//.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(new File(".\\src\\test\\resources\\data\\CreateUserSchema.json"));
		//.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateUserSchema.json"));
		.body(JsonSchemaValidator.matchesJsonSchema(new File(".\\\\src\\\\test\\\\resources\\\\data\\\\CreateUserSchema.json")));
		
		
		
	}

}
