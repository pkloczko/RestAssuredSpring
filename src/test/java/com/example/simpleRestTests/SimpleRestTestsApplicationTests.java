package com.example.simpleRestTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.Data;
import model.User;
import model.UserData;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleRestTestsApplicationTests {

	@Value("${base.url}")
	private String baseUrl;

	UserData user = new UserData("1","george.bluth@reqres.in","George","Bluth","https://reqres.in/img/faces/1-image.jpg");

	@Test
	void getHeaders(){
		String basePath = "/api/users";
		given()
				.contentType(ContentType.JSON)
				.baseUri(baseUrl).basePath(basePath)
		.when()
				.get()
		.then().statusCode(HttpStatus.OK.value())
				.log().status()
				.log().headers();
	}


	@Test
	void getUserById() {
		String basePath = "/api/users/1";

		RequestSpecification requestSpecification = given()
				.contentType(ContentType.JSON)
				.baseUri(baseUrl).basePath(basePath);
		Response response = requestSpecification.get();

		response.then()
				.log().all()
				.assertThat().statusCode(HttpStatus.OK.value())
				.assertThat().body("data.first_name", CoreMatchers.equalTo(user.getUserName()));
	}

	@Test
	public void getUsers(){
		String basePath = "/api/users";
		RequestSpecification requestSpecification = RestAssured.given().baseUri(baseUrl).basePath(basePath);
		Response response = requestSpecification.get();
		ValidatableResponse validatableResponse = response.then()
				.statusCode(200)
				.log().all();

		User responseUsers =  validatableResponse.extract().as(User.class);
		List<Data> dataList = responseUsers.getData();
		for (Data data: dataList) {
			Assert.assertNotNull(data.getId());
		}


		//https://www.toolsqa.com/rest-assured/deserialize-json-response/
		//https://www.james-willett.com/rest-assured-extract-json-response/
		//assertThat(result1234).isEqualTo(mainClass); //all values

		//ExpectedJson eqaul to actualjson

		//MainClass responseEntitiy = response.body().as(MainClass.class);
		//response1

		//System.out.println(responseEntitiy.getData().getFirst_name());
	}

}
